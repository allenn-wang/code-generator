package com.allenn.generator.application;

import com.allenn.generator.entity.Configuration;
import com.allenn.generator.entity.Table;
import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.utils.DBFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = ConfigUtil.getConfiguration();
        List<Table> tables = DBFactory.getDBHandler(configuration.getDb()).getAllTables();
    }
}
