package com.allenn.generator.entity;

import java.io.Serializable;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class Column implements Serializable {
    private static final long serialVersionUID = 4002682879447790358L;

    // 列名
    private String name;

    // 注释
    private String annotation;

    // jdbc数据类型
    private String jdbcType;

    // 是否主键
    private boolean isPrimarykey;

    // 允许为空
    private boolean isNullAble;

    // 字段长度
    private Integer length;

    // 字段精度
    private Integer scale;

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
        return isPrimarykey;
    }

    public void setPrimarykey(boolean primarykey) {
        isPrimarykey = primarykey;
    }

    public boolean isNullAble() {
        return isNullAble;
    }

    public void setNullAble(boolean nullAble) {
        isNullAble = nullAble;
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

}
