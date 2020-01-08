package com.allenn.generator.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class Column implements Serializable {
    private static final long serialVersionUID = 4002682879447790358L;

    // 数据库表字段名
    private String name;

    // 字段注释
    private String annotation;

    // 数据库字段类型
    private String jdbcType;

    // 对应java类型
    private String javaType;

    // 是否是主键
    private boolean primarykey;

    // 数据库是否为空
    private boolean nullAble;

    // 字段长度
    private Integer length;

    // 字段精度
    private Integer scale;

    // 去下划线的驼峰java属性名
    private String propertyName;

    // 业务注释属性
    private ColumnCommentOption commentOption;

    // 外键关联表
    private Table joinTable;

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

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public boolean isPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(boolean primarykey) {
        this.primarykey = primarykey;
    }

    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public ColumnCommentOption getCommentOption() {
        return commentOption;
    }

    public void setCommentOption(ColumnCommentOption commentOption) {
        this.commentOption = commentOption;
    }

    public Table getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(Table joinTable) {
        this.joinTable = joinTable;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", annotation='" + annotation + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", primarykey=" + primarykey +
                ", nullAble=" + nullAble +
                ", length=" + length +
                ", scale=" + scale +
                ", javaType='" + javaType + '\'' +
                ", propertyName='" + propertyName + '\'' +
                '}';
    }
}
