package com.allenn.generator.db;

import com.allenn.generator.db.base.DBHandler;
import com.allenn.generator.entity.Configuration;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class DBFactory {
    public static DBHandler getDBHandler(Configuration.Db dbConfig) {
        String url = dbConfig.getUrl();
        if (url.contains("mysql")) {
            return new MysqlDBHandler();
        }
        /*else if (url.contains("oracle")) {
            return new OracleDBHandler();
        }
        else if (url.contains("postgre")) {
            return new PostgreDBHandler();
        }*/
        return null;
    }
}
