package com.allenn.generator.db.base;

import com.allenn.generator.entity.Table;

import java.util.List;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public abstract class AbstractDBHandler implements DBHandler {

    @Override
    public List<Table> getAllTables() {
        return null;
    }
}
