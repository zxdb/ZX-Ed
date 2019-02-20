package br.com.summa.zxed.sql;

import java.util.*;

import org.openxava.model.meta.*;
import org.openxava.util.*;

import br.com.summa.sol.util.*;
import br.com.summa.sol.util.Maps;
import br.com.summa.zxed.ex.*;

public class SqlBuilder {
    public static final Set<String> excludedFields = Sets.asHashSet(
            "__MODEL_NAME__",
            "authorRoles",
            "firstPublisher");

    private final Map<String, String> allColumns = new TreeMap<String, String>();
    private final Map<String, String> keyColumns = new TreeMap<String, String>();
    private final Map<String, String> oldColumns = new TreeMap<String, String>();
    private final String modelName;

    public SqlBuilder(String modelName) {
        this.modelName = modelName;
    }

    private String getTableName() {
        return ZxdbNamingStrategy.INSTANCE.classToTableName(modelName);
    }

    private String getColumnName(String fieldName) {
    	return ZxdbNamingStrategy.INSTANCE.columnName(fieldName);
    }

    private String getVersionName() {
        String fieldName = MetaModel.get(modelName).getVersionPropertyName();
        return fieldName != null ? getColumnName(fieldName) : "?";
    }

    private static String quoteValue(Object value) {
        return value == null || "".equals(value) ? "null" :
            value instanceof Boolean || value instanceof Number ? value.toString() :
            value instanceof Enum<?> ? ""+((Enum<?>)value).ordinal() :
            "'"+value.toString().replaceAll("'", "''")+"'";
    }

    @SuppressWarnings("unchecked")
    private void convertFieldsToColumns(Map<String, String> columns, String prefix, Map<String, Object> fields) {
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            if (fieldValue instanceof Map) {
                convertFieldsToColumns(columns, fieldName+".", (Map<String, Object>)fieldValue);
            } else if (!excludedFields.contains(fieldName)) {
            	if (fieldName.equals(getVersionName()) && !prefix.isEmpty()) {
                    // FIXME: HACK!!!
                } else {
                	columns.put(getColumnName(prefix+fieldName), quoteValue(fieldValue));
                }
            }
        }
    }

    public SqlBuilder set(Map<String, Object> allFields) {
        convertFieldsToColumns(allColumns, "", allFields);
        return this;
    }

    public SqlBuilder where(Map<String, Object> keyFields) {
        convertFieldsToColumns(keyColumns, "", keyFields);
        return this;
    }

    public SqlBuilder was(Map<String, Object> oldFields) {
        convertFieldsToColumns(oldColumns, "", oldFields);
        return this;
    }

    public String insert() {
        // Key columns are inserted separately so they will appear first
        Maps.removeAll(allColumns, keyColumns);

        // Omit version column so it's initialized implicitly
        allColumns.remove(getVersionName());

        // Build sql
        String columnNames = Chains.merge(", ", Chains.traverse(", ", keyColumns.keySet()), Chains.traverse(", ", allColumns.keySet()));
        String columnValues = Chains.merge(", ", Chains.traverse(", ", keyColumns.values()), Chains.traverse(", ", allColumns.values()));
        return "INSERT INTO "+getTableName()+"("+columnNames+") VALUES ("+columnValues+")";
    }

    public String update(boolean versioned) throws Exception {
        // Only modified columns should be updated
        Maps.removeAll(allColumns, oldColumns);

        // If there's nothing to save then exit
        if (allColumns.size() == 0) {
        	throw new NothingToSaveException();
        }

        // If version column was modified, then someone else changed it
        String versionName = getVersionName();
        if (allColumns.containsKey(versionName)) {
            throw new XavaException("concurrency_error");
        }
        String versionValue = oldColumns.get(versionName);

        // Build sql
        return "UPDATE "+getTableName()+" SET "+Chains.traverse(", ", allColumns.entrySet())+
                (versioned && versionValue != null ? ", "+versionName+"="+versionName+"+1" : "")+
                " WHERE "+Chains.traverse(" AND ", keyColumns.entrySet())+
                (versioned && versionValue != null ? " AND "+versionName+"="+versionValue : "");
    }

    public String delete(boolean versioned) {
        // Use key columns only
        String versionName = getVersionName();
        keyColumns.remove(versionName);
        String versionValue = oldColumns.get(versionName);

        // Build sql
        return "DELETE FROM "+getTableName()+" WHERE "+Chains.traverse(" AND ", keyColumns.entrySet())+
                (versioned && versionValue != null ? " AND "+versionName+"="+versionValue : "");
    }
}
