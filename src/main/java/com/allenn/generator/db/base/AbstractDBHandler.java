package com.allenn.generator.db.base;

import com.allenn.generator.entity.Column;
import com.allenn.generator.entity.Table;
import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.utils.StringUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-20
 */
public abstract class AbstractDBHandler implements DBHandler {
    protected String driverClass;

    public AbstractDBHandler (String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public List<Table> getAllTables() {
        List<Table> tables = new LinkedList<>();
        Connection connection = null;
        try {
            Class.forName(driverClass);
            String url = ConfigUtil.getConfiguration().getDb().getUrl();
            String username = ConfigUtil.getConfiguration().getDb().getUsername();
            String password = ConfigUtil.getConfiguration().getDb().getPassword();
            connection = DriverManager.getConnection(url, username, password);

        }  catch (Exception e) {
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
                table.setClassName(getJavaName(table.getName()));
                tables.add(table);
            }
        }  catch (Exception e) {
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

    public String getJavaName(String name) {
        String[] arrStr = name.split("_");
        String javaName = "";
        for (String s : arrStr) {
            javaName += StringUtil.upperFirstChar(s.toLowerCase());
        }
        return javaName;
    }

    public abstract String getJavaType(String jdbcType, Integer length, Integer scale);

    private List<Column> buildColumn(ResultSet resultSet) {
        List<Column> columns = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Column column = new Column();
                column.setName(resultSet.getString(LABEl_NAME));
                column.setAnnotation(resultSet.getString(LABEL_ANNOTATION));
                column.setJdbcType(resultSet.getString(LABEL_JDBCTYPE));
                column.setNullAble(resultSet.getBoolean(LABEL_NULLABLE));
                column.setPrimarykey(resultSet.getBoolean(LABEL_PRIMARYKEY));
                column.setLength(resultSet.getInt(LABEL_LENGTH));
                column.setScale(resultSet.getInt(LABEL_SCALE));
                column.setPropertyName(StringUtil.lowerFirstChar(getJavaName(column.getName())));
                column.setJavaType(getJavaType(column.getJdbcType(), column.getLength(), column.getScale()));
                columns.add(column);
            }
        }  catch (Exception e) {
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

    private ResultSet queryForResult (Connection connection, String sql, Object[] params) {
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
        } finally {
            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultSet;
    }
}
