package com.allenn.generator.entity;

import java.util.Map;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class SysConfig {
    private String moduleName;

    private Map<String, com.allenn.generator.entity.Configuration> modules;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Map<String, com.allenn.generator.entity.Configuration> getModules() {
        return modules;
    }

    public void setModules(Map<String, com.allenn.generator.entity.Configuration> modules) {
        this.modules = modules;
    }
}
