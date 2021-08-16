package br.com.summa.zxed.sql;

import org.hibernate.boot.model.naming.*;
import org.hibernate.cfg.*;
import org.hibernate.engine.jdbc.env.spi.*;

public class ZxdbNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final long serialVersionUID = 1L;

    public static final PhysicalNamingStrategyStandardImpl INSTANCE = new ZxdbNamingStrategy();

    public static String plural(String name) {
        return (name.endsWith("y") ? name.substring(0, name.length()-1) + "ies" :
            name.endsWith("s") ? name + "es" :
                name + "s").toLowerCase();
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return new Identifier(plural(ImprovedNamingStrategy.INSTANCE.tableName(name.getText())), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String columnName = name.getText();
        int i = columnName.lastIndexOf('_', columnName.lastIndexOf('_')-1);
        if (i != -1) {
            columnName = columnName.substring(i+1);
        }
        return new Identifier(ImprovedNamingStrategy.INSTANCE.columnName(columnName), name.isQuoted());
    }
}
