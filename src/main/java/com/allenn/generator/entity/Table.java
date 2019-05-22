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

    private String name;
    private String annotation;
    private String className;
    private List<Column> columnList;
    private Column primaryKeyColumn;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public Column getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(Column primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }
}
