package com.allenn.generator.entity;

import com.allenn.generator.constants.Constant;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class Table implements Serializable {
    private static final long serialVersionUID = -1291782123288833803L;

    // 数据库表名
    private String name;

    // 表名注释
    private String annotation;

    // 去下划线的驼峰java类名
    private String className;

    // 所有列
    private List<Column> columnList = new LinkedList<>();

    // 主键列
    private Column primaryKeyColumn;

    // 外键列
    private List<Column> foreignKeyCols = new LinkedList<>();

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

    public List<com.allenn.generator.entity.Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<com.allenn.generator.entity.Column> columnList) {
        this.columnList = columnList;
    }

    public com.allenn.generator.entity.Column getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(com.allenn.generator.entity.Column primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

    public List<com.allenn.generator.entity.Column> getForeignKeyCols() {
        return foreignKeyCols;
    }

    public void setForeignKeyCols(List<com.allenn.generator.entity.Column> foreignKeyCols) {
        this.foreignKeyCols = foreignKeyCols;
    }

    public void doInitBuild(Map<String, Table> tableMap) {
        this.primaryKeyColumn = this.columnList.stream().filter(c -> c.isPrimarykey()).findFirst().get();
        this.foreignKeyCols = this.columnList.stream()
                .filter(col -> Constant.FieldServiceType.JOIN.equalsIgnoreCase(col.getCommentOption().getDataType()))
                .collect(Collectors.toList());
    }
}
