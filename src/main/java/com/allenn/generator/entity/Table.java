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

    // 表业务注释
    private TableCommentOption commentOption;

    // 去下划线的驼峰java类名
    private String className;

    // 所有列
    private List<Column> columnList = new LinkedList<>();

    // 主键列
    private Column primaryKeyColumn;

    // 关键字like搜索列
    private List<Column> quickSearchCols = new LinkedList<>();

    // 外键列
    private List<Column> foreignKeyCols = new LinkedList<>();

    // 简要信息列 用于生成简要信息查询sql
    private List<Column> briefShowCols = new LinkedList<>();

    // 1对多 子表
    private List<Table> foreignChildTables = new LinkedList<>();

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

    public List<com.allenn.generator.entity.Column> getQuickSearchCols() {
        return this.quickSearchCols;
    }

    public void setQuickSearchCols(List<com.allenn.generator.entity.Column> quickSearchCols) {
        this.quickSearchCols = quickSearchCols;
    }

    public List<com.allenn.generator.entity.Column> getForeignKeyCols() {
        return foreignKeyCols;
    }

    public void setForeignKeyCols(List<com.allenn.generator.entity.Column> foreignKeyCols) {
        this.foreignKeyCols = foreignKeyCols;
    }

    public List<com.allenn.generator.entity.Column> getBriefShowCols() {
        return briefShowCols;
    }

    public void setBriefShowCols(List<com.allenn.generator.entity.Column> briefShowCols) {
        this.briefShowCols = briefShowCols;
    }

    public List<Table> getForeignChildTables() {
        return foreignChildTables;
    }

    public void setForeignChildTables(List<Table> foreignChildTables) {
        this.foreignChildTables = foreignChildTables;
    }

    public TableCommentOption getCommentOption() {
        return commentOption;
    }

    public void setCommentOption(TableCommentOption commentOption) {
        this.commentOption = commentOption;
    }

    public void doInitBuild(Map<String, Table> tableMap) {
        this.primaryKeyColumn = this.columnList.stream().filter(c -> c.isPrimarykey()).findFirst().get();
        this.quickSearchCols = this.columnList.stream()
                .filter(col -> col.getCommentOption().isQuickSearch())
                .collect(Collectors.toList());
        this.foreignKeyCols = this.columnList.stream()
                .filter(col -> Constant.FieldServiceType.JOIN.equalsIgnoreCase(col.getCommentOption().getDataType()))
                .collect(Collectors.toList());
        this.briefShowCols = this.columnList.stream().filter(col -> col.getCommentOption().isBriefShow())
                .collect(Collectors.toList());
        // 默认添加主键
        if (briefShowCols == null || briefShowCols.size() == 0) {
            briefShowCols = new LinkedList<>();
            briefShowCols.add(this.primaryKeyColumn);
        } else {
            if (!this.briefShowCols.stream().anyMatch(b -> b.isPrimarykey())) {
                briefShowCols.add(0, this.primaryKeyColumn);
            }
        }

        List<String> childTableNames = this.primaryKeyColumn.getCommentOption().getChildTables();
        if (childTableNames != null && childTableNames.size() > 0) {
            this.foreignChildTables = childTableNames.parallelStream()
                    .map(t -> tableMap.get(t)).collect(Collectors.toList());
        }
    }
}
