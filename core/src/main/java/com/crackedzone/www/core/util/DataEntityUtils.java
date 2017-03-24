package com.crackedzone.www.core.util;

import jodd.bean.BeanUtil;
import jodd.bean.BeanUtilBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Package com.smartchoiceads.sdk.business.util
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
public class DataEntityUtils {

    private static BeanUtil beanUtil = new BeanUtilBean();

    private static ConcurrentHashMap<String, String> cacheInsertSql = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, String> cacheUpdateSql = new ConcurrentHashMap<>();

    public static String generateNamedInsertClause(Class<?> clazz) {
        String tableName = DataEntityUtils.getTableName(clazz);
        return generateNamedInsertClause(clazz, tableName);
    }

    public static String generateNamedInsertClause(Class<?> clazz, String tableName) {
        if (cacheInsertSql.containsKey(tableName)) return cacheInsertSql.get(tableName);

        StringBuilder columns = new StringBuilder();
        StringBuilder values  = new StringBuilder();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (null != field.getAnnotation(GeneratedValue.class))
                continue;
            Column columnNameInAnnotation = field.getAnnotation(Column.class);
            String columnValue            = field.getName();
            String columnField            = (null == columnNameInAnnotation) ? columnValue : columnNameInAnnotation.name();
            columns.append("`").append(columnField).append("`").append(", ");
            values.append(":").append(columnValue).append(", ");
        }

        columns.deleteCharAt(columns.length() - 2);
        values.deleteCharAt(values.length() - 2);

        String sql = "INSERT INTO %s(%s) VALUES (%s)";
        sql = String.format(sql, tableName, columns, values);
        cacheInsertSql.put(tableName, sql);
        return sql;
    }

    public static String generateNamedUpdateClause(Class<?> clazz, String keyColumn) {
        String tableName = DataEntityUtils.getTableName(clazz);
        return generateNamedUpdateClause(clazz, tableName, keyColumn);
    }

    public static String generateNamedUpdateClause(Class<?> clazz, String tableName, String conditionFieldName) {
        if (cacheUpdateSql.containsKey(tableName + conditionFieldName))
            return cacheUpdateSql.get(tableName + conditionFieldName);

        String conditionFieldValue = null;
        try {
            Field  conditionField         = clazz.getDeclaredField(conditionFieldName);
            Column columnNameInAnnotation = conditionField.getAnnotation(Column.class);
            conditionFieldValue = conditionField.getName();
            conditionFieldName = (null == columnNameInAnnotation) ? conditionFieldValue : columnNameInAnnotation.name();
        } catch (NoSuchFieldException e) {
            conditionFieldValue = conditionFieldName;
        }

        StringBuilder columns = new StringBuilder();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (null != field.getAnnotation(GeneratedValue.class))
                continue;
            Column columnNameInAnnotation = field.getAnnotation(Column.class);
            String columnValue            = field.getName();
            String columnField            = (null == columnNameInAnnotation) ? columnValue : columnNameInAnnotation.name();
            columns.append("`").append(columnField).append("`").append(" = ").append(":").append(columnValue).append(", ");
        }

        columns.deleteCharAt(columns.length() - 2);

        String sql = "UPDATE %s SET %s WHERE %s = :%s";
        sql = String.format(sql, tableName, columns.toString(), conditionFieldName, conditionFieldValue);
        cacheUpdateSql.put(tableName + conditionFieldName, sql);
        return sql;
    }

    public static MapSqlParameterSource generateInsertMapParameterSource(Object entity) {
        return generateInsertMapParameterSource(entity, entity.getClass());
    }


    public static MapSqlParameterSource generateInsertMapParameterSource(Object entity, Class<?> clazz) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
            mapSqlParameterSource.addValue(field.getName(), beanUtil.getProperty(entity, field.getName()));
        return mapSqlParameterSource;
    }

    public static MapSqlParameterSource generateUpdateMapParameterSource(Object entity, String conditionFieldName) {
        return generateUpdateMapParameterSource(entity, conditionFieldName, entity.getClass());
    }

    public static MapSqlParameterSource generateUpdateMapParameterSource(Object entity, String conditionFieldName, Class<?> clazz) {
        MapSqlParameterSource mapSqlParameterSource = generateInsertMapParameterSource(entity, clazz);
        try {
            Field  conditionField         = clazz.getDeclaredField(conditionFieldName);
            Column columnNameInAnnotation = conditionField.getAnnotation(Column.class);
            String conditionFieldValue    = conditionField.getName();
            conditionFieldName = (null == columnNameInAnnotation) ? conditionFieldValue : columnNameInAnnotation.name();
        } catch (NoSuchFieldException e) {
        }
        return mapSqlParameterSource.addValue(conditionFieldName, beanUtil.getProperty(entity, conditionFieldName));
    }

    public static String getTableName(Class<?> entityClazz) {
        String tableName             = null;
        Table  tableNameInAnnotation = (Table) entityClazz.getAnnotation(Table.class);
        if (tableNameInAnnotation != null)
            return tableNameInAnnotation.name();
        tableName = entityClazz.getSimpleName();
        if (!tableName.contains("Entity"))
            return tableName;
        return tableName.substring(0, tableName.indexOf("Entity"));
    }
}
