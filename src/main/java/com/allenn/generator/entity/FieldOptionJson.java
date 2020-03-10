package com.allenn.generator.entity;

/**
 * @Description: 业务说明json对象
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class FieldOptionJson {

    // 业务类型为join时, 外键关联的表名
    private String table;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
