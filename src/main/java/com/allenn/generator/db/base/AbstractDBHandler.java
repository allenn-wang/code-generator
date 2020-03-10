package com.allenn.generator.db.base;

import com.alibaba.fastjson.JSON;
import com.allenn.generator.constants.Constant;
import com.allenn.generator.entity.*;
import com.allenn.generator.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public abstract class AbstractDBHandler implements DBHandler {
    protected String driverClass;

    public AbstractDBHandler(String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public List<Table> getAllTables() {
        List<Table> tables = new LinkedList<>();
        Connection connection = null;
        try {
            Class.forName(driverClass);
            String url = com.allenn.generator.utils.ConfigUtil.getConfiguration().getDb().getUrl();
            String username = com.allenn.generator.utils.ConfigUtil.getConfiguration().getDb().getUsername();
            String password = com.allenn.generator.utils.ConfigUtil.getConfiguration().getDb().getPassword();
            connection = DriverManager.getConnection(url, username, password);
            tables = queryTables(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tables;
    }

    protected abstract List<Table> queryTables(Connection connection);

    /**
     * only table info, exclude column info
     *
     * @param connection
     * @param tableSql
     * @param params
     * @return
     */
    protected List<Table> queryTablesDefault(Connection connection, String tableSql, Object[] params) {
        return buildTable(queryForResult(connection, tableSql, params));
    }

    /**
     * column info
     *
     * @param connection
     * @param columnSql
     * @param params
     * @return
     */
    protected List<Column> queryColumnsDefault(Connection connection, String columnSql, Object[] params) {
        return buildColumn(queryForResult(connection, columnSql, params));
    }

    private List<Table> buildTable(ResultSet resultSet) {
        List<Table> tables = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Table table = new Table();
                table.setName(resultSet.getString(LABEl_NAME));
                table.setAnnotation(resultSet.getString(LABEL_ANNOTATION));
                table.setClassName(StringUtil.dbName2JavaName(table.getName()));
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tables;
    }

    public abstract String getJavaType(String jdbcType);

    public abstract String getJdbcType(String jdbcType);

    private List<Column> buildColumn(ResultSet resultSet) {
        List<Column> columns = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Column column = new Column();
                column.setName(resultSet.getString(LABEl_NAME));
                column.setAnnotation(resultSet.getString(LABEL_ANNOTATION));
                column.setJdbcType(getJdbcType(resultSet.getString(LABEL_JDBCTYPE)));
                column.setNullAble(resultSet.getString(LABEL_NULLABLE).equalsIgnoreCase("YES") ? true : false);
                column.setPrimarykey(resultSet.getBoolean(LABEL_PRIMARYKEY));
                column.setLength(resultSet.getInt(LABEL_LENGTH));
                column.setScale(resultSet.getInt(LABEL_SCALE));
                column.setJavaType(getJavaType(column.getJdbcType()));
                column.setPropertyName(StringUtil.lowerFirstChar(StringUtil.dbName2JavaName(column.getName())));
                column.setCommentOption(buildCommentOption(column));
                columns.add(column);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columns;
    }

    private ColumnCommentOption buildCommentOption(Column column) {
        ColumnCommentOption columnCommentOption = new ColumnCommentOption();
        String annotation = column.getAnnotation();
        if (StringUtils.isNotEmpty(annotation)) {
            String[] anns = annotation.split("!");
            columnCommentOption.setLabel(anns[0]);
            columnCommentOption.setDataType(anns[1].toLowerCase());
            if (anns.length > 2) {
                FieldOptionJson optionJson = JSON.parseObject(anns[2], FieldOptionJson.class);
                columnCommentOption.setJoinTable(optionJson.getTable());
                if (Constant.FieldServiceType.ENUM.equals(columnCommentOption.getDataType())) {
                    columnCommentOption.setAllowValues(JSON.parseObject(anns[anns.length - 1], Map.class));
                }
            }
        }
        return columnCommentOption;
    }

    private ResultSet queryForResult(Connection connection, String sql, Object[] params) {
        PreparedStatement preStat = null;
        ResultSet resultSet = null;
        try {
            preStat = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preStat.setObject(i + 1, params[i]);
                }
            }
            resultSet = preStat.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
