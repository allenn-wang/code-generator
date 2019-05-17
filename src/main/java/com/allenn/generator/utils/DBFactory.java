package com.allenn.generator.utils;

import com.allenn.generator.db.MysqlDBHandler;
import com.allenn.generator.db.base.DBHandler;
import com.allenn.generator.entity.Configuration;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class DBFactory {
    public static DBHandler getDBHandler(Configuration.Db dbConfig) {
        return new MysqlDBHandler();
    }
}
