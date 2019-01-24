package br.com.summa.zxed.sql;

import org.hibernate.cfg.*;

public class ZxdbNamingStrategy extends ImprovedNamingStrategy {
    private static final long serialVersionUID = 1L;

    public static final ZxdbNamingStrategy INSTANCE = new ZxdbNamingStrategy();

    public static String plural(String name) {
        return (name.endsWith("y") ? name.substring(0, name.length()-1) + "ies" :
            name.endsWith("s") ? name + "es" :
                name + "s").toLowerCase();
    }

    @Override
    public String classToTableName(String className) {
        return plural(super.classToTableName(className));
    }

    @Override
    public String columnName(String columnName) {
    	int i = columnName.lastIndexOf('.', columnName.lastIndexOf('.')-1);
    	if (i != -1) {
    		columnName = columnName.substring(i+1);
    	}
    	return super.columnName(columnName);
    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return referencedColumnName.contains("_") ? referencedColumnName :
            super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName) + "_" + referencedColumnName;
    }
}
