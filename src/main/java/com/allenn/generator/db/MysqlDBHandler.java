package com.allenn.generator.db;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.db.base.AbstractDBHandler;
import com.allenn.generator.entity.Column;
import com.allenn.generator.entity.Table;
import com.allenn.generator.utils.ConfigUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class MysqlDBHandler extends AbstractDBHandler {
    private String tableSql = "select table_name as name, table_comment as annotation " +
            "from information_schema.tables where table_schema = ?";

    private String columnSql = "select column_name as name, data_type as jdbctype, " +
            "  ifnull(numeric_precision, ifnull(character_maximum_length, 0)) as length," +
            "  numeric_scale as scale, is_nullable as nullAble, column_comment as annotation, " +
            "  case when column_key = 'PRI' then true else false end as primarykey" +
            " from information_schema.columns" +
            " where table_schema = ? and table_name = ? order by ordinal_position asc";

    public MysqlDBHandler() {
        // super("com.mysql.jdbc.Driver");
        super("com.mysql.cj.jdbc.Driver");
    }

    @Override
    protected List<Table> queryTables(Connection connection) {
        List<Table> tables = queryTablesDefault(connection, tableSql,
                new Object[]{ConfigUtil.getConfiguration().getDataBaseName()});
        Map<String, Table> tableMap =
                tables.parallelStream().collect(Collectors.toMap(Table::getName, t -> t));

        for (Table table : tables) {
            List<Column> columnList = queryColumnsDefault(connection, columnSql,
                    new Object[]{ConfigUtil.getConfiguration().getDataBaseName(), table.getName()});
            table.setColumnList(columnList);
            table.doInitBuild(tableMap);

            for (Column col : table.getForeignKeyCols()) {
                col.setJoinTable(tableMap.get(col.getCommentOption().getJoinTable()));
            }
        }


        return tables;
    }

    @Override
    public String getJavaType(String jdbcType) {
        String javaType = Constant.JavaType._STRING;
        if (jdbcType.equalsIgnoreCase("TINYINT")
                || jdbcType.equalsIgnoreCase("SMALLINT")
                || jdbcType.equalsIgnoreCase("INTEGER")
                || jdbcType.equalsIgnoreCase("MEDIUMINT")
                || jdbcType.equalsIgnoreCase("INT")) {
            javaType = Constant.JavaType._INTEGER;
        } else if (jdbcType.equalsIgnoreCase("BIGINT")
                || jdbcType.equalsIgnoreCase("DECIMAL")) {
            javaType = Constant.JavaType._BIGDECIMAL;
        } else if (jdbcType.equalsIgnoreCase("DOUBLE")) {
            javaType = Constant.JavaType._DOUBLE;
        } else if (jdbcType.equalsIgnoreCase("FLOAT")) {
            javaType = Constant.JavaType._FLOAT;
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

    @Override
    public String getJdbcType(String jdbcType) {
        if (jdbcType.equalsIgnoreCase("TEXT")) {
            jdbcType = "LONGVARCHAR";
        } else if (jdbcType.equalsIgnoreCase("ENUM")) {
            jdbcType = "CHAR";
        } else if (jdbcType.equalsIgnoreCase("INT")) {
            jdbcType = "INTEGER";
        }
        return jdbcType.toUpperCase();
    }
}
