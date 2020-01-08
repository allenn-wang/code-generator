package com.allenn.generator.db.base;

import com.allenn.generator.entity.Table;

import java.util.List;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public interface DBHandler {
    String LABEl_NAME = "name";
    String LABEL_ANNOTATION = "annotation";
    String LABEL_JDBCTYPE = "jdbcType";
    String LABEL_PRIMARYKEY = "primarykey";
    String LABEL_NULLABLE = "nullAble";
    String LABEL_LENGTH = "length";
    String LABEL_SCALE = "scale";

    List<Table> getAllTables();
}
