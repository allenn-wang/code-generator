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
    private boolean ignoreTableNamePrefix = false;
    private Configuration configuration = com.allenn.generator.utils.ConfigUtil.getConfiguration();

    public AbstractDBHandler(String driverClass) {
        this.driverClass = driverClass;
        ignoreTableNamePrefix =
                "true".equalsIgnoreCase(com.allenn.generator.utils.ConfigUtil.getConfiguration().getIgnoreTableNamePrefix());
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
                String tableClassNameTmp = table.getName();
                if (ignoreTableNamePrefix) {
                    tableClassNameTmp = tableClassNameTmp.substring(tableClassNameTmp.indexOf("_"));
                }
                table.setClassName(StringUtil.dbName2JavaName(tableClassNameTmp));
                table.setCommentOption(buildTableCommentOption(table));
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

    private TableCommentOption buildTableCommentOption(Table table) {
        TableCommentOption tableCommentOption = new TableCommentOption();
        String tableAnnotation = table.getAnnotation();
        if (StringUtils.isNotEmpty(tableAnnotation)) {
            String[] anns = tableAnnotation.split("!");
            table.setAnnotation(anns[0]);
            if (anns.length > 1) {
                FieldOptionJson fieldOptionJson = JSON.parseObject(anns[1], FieldOptionJson.class);
                if ("true".equalsIgnoreCase(fieldOptionJson.getCacheEnable())) {
                    tableCommentOption.setCacheEnable(true);

                    String regStr = "^\\d{1,11}(s|S|m|M|h|H|D|d)?$";
                    String singleCacheExpire = fieldOptionJson.getSingleCacheExpire();
                    if (StringUtils.isNotEmpty(singleCacheExpire)) {
                        if (Pattern.matches(regStr, singleCacheExpire)) {
                            if (StringUtils.isNumeric(singleCacheExpire)) {
                                tableCommentOption.setSingleCacheExpire(Integer.valueOf(singleCacheExpire));
                            } else {
                                tableCommentOption.setSingleCacheExpire(
                                        Integer.valueOf(singleCacheExpire.substring(0, singleCacheExpire.length() - 1)));
                                char unit = singleCacheExpire.charAt(singleCacheExpire.length() - 1);
                                if (unit == 'm' || unit == 'M') {
                                    tableCommentOption.setSingleCacheUnit(TimeUnit.MINUTES.name());
                                } else if (unit == 'h' || unit == 'H') {
                                    tableCommentOption.setSingleCacheUnit(TimeUnit.HOURS.name());
                                } else if (unit == 'd' || unit == 'D') {
                                    tableCommentOption.setSingleCacheUnit(TimeUnit.DAYS.name());
                                }
                            }
                        }
                    }

                    String listCacheExpire = fieldOptionJson.getListCacheExpire();
                    if (StringUtils.isNotEmpty(listCacheExpire)) {
                        if (Pattern.matches(regStr, listCacheExpire)) {
                            if (StringUtils.isNumeric(listCacheExpire)) {
                                tableCommentOption.setListCacheExpire(Integer.valueOf(listCacheExpire));
                            } else {
                                tableCommentOption.setListCacheExpire(
                                        Integer.valueOf(listCacheExpire.substring(0, listCacheExpire.length() - 1)));
                                char unit = listCacheExpire.charAt(listCacheExpire.length() - 1);
                                if (unit == 'm' || unit == 'M') {
                                    tableCommentOption.setListCacheUnit(TimeUnit.MINUTES.name());
                                } else if (unit == 'h' || unit == 'H') {
                                    tableCommentOption.setListCacheUnit(TimeUnit.HOURS.name());
                                } else if (unit == 'd' || unit == 'D') {
                                    tableCommentOption.setListCacheUnit(TimeUnit.DAYS.name());
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!tableCommentOption.isCacheEnable() && configuration.getCacheTables().contains(table.getName())) {
            tableCommentOption.setCacheEnable(true);
        }

        return tableCommentOption;
    }

    private ColumnCommentOption buildCommentOption(Column column) {
        ColumnCommentOption columnCommentOption = new ColumnCommentOption();
        String annotation = column.getAnnotation();
        if (StringUtils.isNotEmpty(annotation)) {
            String[] anns = annotation.split("!");
            columnCommentOption.setLabel(anns[0]);
            columnCommentOption.setDataType(anns[1].toLowerCase());

            // 字符串类型 先取数据库级别最大长度
            if (Constant.JavaType._STRING.equals(column.getJavaType())) {
                columnCommentOption.setMaxLength(column.getLength().toString());
            }

            if (Constant.FieldServiceType.BOOLEAN.equals(columnCommentOption.getDataType())) {
                Map<String, String> booleans = new HashMap<>();
                booleans.put("0", "否");
                booleans.put("1", "是");
                columnCommentOption.setAllowValues(booleans);
            } else if (Constant.FieldServiceType.ENUM.equals(columnCommentOption.getDataType())) {
                if (anns.length == 3) {
                    columnCommentOption.setAllowValues(JSON.parseObject(anns[2], Map.class));
                } else if (anns.length == 4) {
                    columnCommentOption.setAllowValues(JSON.parseObject(anns[3], Map.class));
                }
            }
            if (anns.length > 2) {
                handleOptionJson(JSON.parseObject(anns[2], FieldOptionJson.class),
                        columnCommentOption, column);
            }
        }
        return columnCommentOption;
    }

    private void handleOptionJson(FieldOptionJson optionJson, ColumnCommentOption columnCommentOption,
                                  Column column) {
        columnCommentOption.setRequired("true".equalsIgnoreCase(optionJson.getRequired()));
        columnCommentOption.setApiHidden(!"true".equalsIgnoreCase(optionJson.getIsApiHidden()));
        columnCommentOption.setQuickSearch("true".equalsIgnoreCase(optionJson.getQuickSearch()));
        columnCommentOption.setHighSearch("true".equalsIgnoreCase(optionJson.getHighSearch()));
        columnCommentOption.setBriefShow("true".equalsIgnoreCase(optionJson.getBriefShow()));
        columnCommentOption.setFormatter(optionJson.getFormatter());
        columnCommentOption.setJoinTable(optionJson.getTable());

        String joinPropertyName = column.getPropertyName();
        if (column.getName().endsWith("_id")) {
            joinPropertyName = joinPropertyName.substring(0, joinPropertyName.length() - 2);
        }
        columnCommentOption.setJoinPropertyName(joinPropertyName);
        columnCommentOption.setMaxValue(
                optionJson.getMaxValue() == null ? null : optionJson.getMaxValue().toString());
        columnCommentOption.setMinValue(
                optionJson.getMinValue() == null ? null : optionJson.getMinValue().toString());
        columnCommentOption.setMaxLength(
                optionJson.getMaxLength() == null ? null : optionJson.getMaxLength());

        if (StringUtils.isNotBlank(optionJson.getChild())) {
            columnCommentOption.setChildTables(
                    Arrays.asList(optionJson.getChild().split(",")));
        }
        if (StringUtils.isNotBlank(optionJson.getSupplement())
                && StringUtils.isNotBlank(optionJson.getSupplementMsg())) {
            Map<String,String> map = new HashMap<>();
            if(optionJson.getSupplement().contains(",") && optionJson.getSupplementMsg().contains(",") ){
                for(int i=0;i<Arrays.asList(optionJson.getSupplement().split(",")).size();i++){
                    map.put(Arrays.asList(optionJson.getSupplement().split(",")).get(i)
                            ,Arrays.asList(optionJson.getSupplementMsg().split(",")).get(i));
                }

            }else{
                map.put(optionJson.getSupplement(),optionJson.getSupplementMsg());
            }
            columnCommentOption.setSupplementMap(map);

        }
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
