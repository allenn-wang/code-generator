package com.allenn.generator.db;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.db.base.AbstractDBHandler;
import com.allenn.generator.entity.Table;
import com.allenn.generator.utils.ConfigUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class MysqlDBHandler extends AbstractDBHandler {
    private String tableSql = "select table_name as name, table_comment as annotation " +
            "from information_schema.tables where table_schema = ?";

    private String columnSql = "select column_name as name, data_type as jdbctype, " +
            "  ifnull(numeric_precision, ifnull(character_maximum_length, 0)) as length," +
            "  numeric_scale as scale, is_nullable as nullAble, column_comment as annotation, " +
            "  case when column_key = 'PRI' then true else false end as primarykey" +
            "from information_schema.columns" +
            "where table_schema = ? and table_name = ? ";

    public MysqlDBHandler () {
        super("com.mysql.jdbc.Driver");
    }

    @Override
    protected List<Table> queryTables(Connection connection) {
        List<Table> tables = queryTablesDefault(connection, tableSql,
                new Object[]{ConfigUtil.getConfiguration().getDataBaseName()});
        for (Table table : tables) {
            table.setColumnList(queryColumnsDefault(connection, columnSql,
                    new Object[]{ConfigUtil.getConfiguration().getDataBaseName(), table.getName()}));
        }
        return tables;
    }

    @Override
    public String getJavaType(String jdbcType, Integer length, Integer scale) {
        String javaType = Constant.JavaType._STRING;
        if (jdbcType.equalsIgnoreCase("TINYINT")
                || jdbcType.equalsIgnoreCase("SMALLINT")
                || jdbcType.equalsIgnoreCase("MEDIUMINT")
                || jdbcType.equalsIgnoreCase("INT")) {
            javaType = Constant.JavaType._INTEGER;
        } else if (jdbcType.equalsIgnoreCase("BIGINT")) {
            javaType = Constant.JavaType._LONG;
        } else if (jdbcType.equalsIgnoreCase("DOUBLE")) {
            javaType = Constant.JavaType._DOUBLE;
        } else if (jdbcType.equalsIgnoreCase("FLOAT")) {
            javaType = Constant.JavaType._FLOAT;
        } else if (jdbcType.equalsIgnoreCase("DECIMAL")) {
            javaType = (scale == null || scale == 0)
                    ? (length < 10 ? Constant.JavaType._INTEGER : Constant.JavaType._LONG)
                    : Constant.JavaType._DOUBLE;;
        } else if (jdbcType.equalsIgnoreCase("DATE")
                || jdbcType.equalsIgnoreCase("DATETIME")
                || jdbcType.equalsIgnoreCase("TIME")
                || jdbcType.equalsIgnoreCase("TIMESTAMP")) {
            javaType = Constant.JavaType._DATE;
        } else if (jdbcType.equalsIgnoreCase("BINARY")
                || jdbcType.equalsIgnoreCase("VARBINARY")
                || jdbcType.equalsIgnoreCase("TINYBLOB")
                || jdbcType.equalsIgnoreCase("MEDIUMBLOB")
                || jdbcType.equalsIgnoreCase("BLOB")
                || jdbcType.equalsIgnoreCase("LONGBLOB")) {
            javaType = Constant.JavaType._BYTE;
        }
        return javaType;
    }
}
