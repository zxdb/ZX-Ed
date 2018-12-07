package br.com.summa.zxed.sql;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.*;

import org.openxava.jpa.*;
import org.openxava.model.*;
import org.openxava.util.*;

public class NativeManager {
    public static final String LOG_FILE = "/tmp/ZX-Ed.sql";
    public static final String GUEST_USER = "guest";

    private NativeManager() {
    }

    public static void insert(String model, Map<String, Object> keyFields, Map<String, Object> allFields) throws Exception {
        SqlBuilder sql = new SqlBuilder(model).set(allFields).where(keyFields);
        execute(sql.insert());
        Object entity = MapFacade.findEntity(model, keyFields);
        String msg = "Created new "+entity;
        audit(msg, sql.insert());
    }

    public static void update(String model, Map<String, Object> keyFields, Map<String, Object> allFields) throws Exception {
        Object entity = MapFacade.findEntity(model, keyFields);
        String msg = "Changed old "+entity;
        Map<String, Object> oldFields = MapFacade.getValues(model, entity, allFields);
        SqlBuilder sql = new SqlBuilder(model).set(allFields).where(keyFields).was(oldFields);
        execute(sql.update(true));
        audit(msg, sql.update(false));
    }

    public static void delete(String model, Map<String, Object> keyFields, Map<String, Object> allFields) throws Exception {
        Object entity = MapFacade.findEntity(model, keyFields);
        String msg = "Removed old "+entity;
        SqlBuilder sql = new SqlBuilder(model).where(keyFields).was(allFields);
        execute(sql.delete(true));
        audit(msg, sql.delete(false));
    }

    private static void execute(String sql) throws Exception {
        if (Users.getCurrent() == null || Users.getCurrent().compareToIgnoreCase(GUEST_USER) == 0) {
            throw new XavaException("unauthorized_user");
        }
        int i = XPersistence.getManager().createNativeQuery(sql).executeUpdate();
        if (i != 1) {
            throw new XavaException("zxed_concurrency");
        }
        XPersistence.commit();
    }

    private static void audit(String msg, String sql) throws Exception {
        String s = "\n-- ["+Users.getCurrent()+"] "+msg+"\n"+sql+";\n";
        File log = new File(LOG_FILE).getAbsoluteFile();
        log.getParentFile().mkdirs();
        Files.write(log.toPath(), s.getBytes("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
