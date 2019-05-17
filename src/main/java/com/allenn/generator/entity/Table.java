package com.allenn.generator.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class Table implements Serializable {
    private static final long serialVersionUID = -1291782123288833803L;

    // 表名
    private String name;

    // 注释
    private String annotation;

    // 字段集合
    private List<Column> columnList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
}
