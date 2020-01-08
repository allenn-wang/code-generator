package com.allenn.generator.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: code-generator\src\main\resources\generator.yaml
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class Configuration implements Serializable {
    private static final long serialVersionUID = 2821325309161293475L;
    // database instance name
    private String dataBaseName;

    // to generate javadoc for author
    private String author;

    // 默认当前项目target/src 配置后直接生成至配置模块core目录,包括 mapper/dao/service/impl
    private String coreProjectName;

    // 默认当前项目target/src 配置后直接生成至配置模块base目录,
    // 包括 BaseController/BaseDao/BaseEntity/BaseService/BaseServiceImpl
    //      BusinessException/BusinessExceptionHandler/IResultCode/ResultCode/IEnum/ResultObject/PageObject
    private String commonProjectName;

    // 生成至<commonProjectName>模块  module.<entityModuleName>目录
    // 包括 DO-data object VO-view object BO-business object
    private String moduleName = this.dataBaseName;

    // 项目基础包名 code's root package, e.g. the service code path: package + "." + path.service
    private String rootPackageName;

    //是否忽略表名前缀
    private String ignoreTableNamePrefix = "false";

    //是否需要缓存
    private String cacheEnable = "false";

    // 是否生成swagger注解
    private String swaggerEnable = "false";

    // the mvc module to generate
    private Path path;

    // database connection info
    private Db db;

    private List<String> systemColumns = new LinkedList<>();

    private List<String> buildTables = new LinkedList<>();

    private List<String> cacheTables = new LinkedList<>();

    public void vaildConfiguration() throws Exception {
        if (StringUtils.isEmpty(this.rootPackageName)) {
            throw new Exception("Unknown rootPackageName.");
        }
        if (StringUtils.isEmpty(this.dataBaseName)) {
            throw new Exception("Unknown dataBaseName.");
        }
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoreProjectName() {
        return coreProjectName;
    }

    public void setCoreProjectName(String coreProjectName) {
        this.coreProjectName = coreProjectName;
    }

    public String getCommonProjectName() {
        return commonProjectName;
    }

    public void setCommonProjectName(String commonProjectName) {
        this.commonProjectName = commonProjectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getRootPackageName() {
        return rootPackageName;
    }

    public void setRootPackageName(String rootPackageName) {
        this.rootPackageName = rootPackageName;
    }

    public String getIgnoreTableNamePrefix() {
        return ignoreTableNamePrefix;
    }

    public void setIgnoreTableNamePrefix(String ignoreTableNamePrefix) {
        this.ignoreTableNamePrefix = ignoreTableNamePrefix;
    }

    public String getCacheEnable() {
        return cacheEnable;
    }

    public void setCacheEnable(String cacheEnable) {
        this.cacheEnable = cacheEnable;
    }

    public String getSwaggerEnable() {
        return swaggerEnable;
    }

    public void setSwaggerEnable(String swaggerEnable) {
        this.swaggerEnable = swaggerEnable;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public List<String> getSystemColumns() {
        return systemColumns;
    }

    public void setSystemColumns(List<String> systemColumns) {
        this.systemColumns = systemColumns;
    }

    public List<String> getBuildTables() {
        return buildTables;
    }

    public void setBuildTables(List<String> buildTables) {
        this.buildTables = buildTables;
    }

    public List<String> getCacheTables() {
        return cacheTables;
    }

    public void setCacheTables(List<String> cacheTables) {
        this.cacheTables = cacheTables;
    }

    public static class Db {
        private String url;
        private String username;
        private String password;

        public Db() {
        }

        public Db(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Path {
        private String controller = "controller";
        private String service = "service";
        private String impl = "service.impl";
        private String dao = "dao";
        private String entity = "entity";
        private String mapper = "mapper";
        private String bo = "bo";

        public Path() {
        }

        public Path(String controller, String service, String impl,
                    String dao, String entity, String mapper, String bo) {
            this.controller = controller;
            this.service = service;
            this.impl = impl;
            this.dao = dao;
            this.entity = entity;
            this.mapper = mapper;
            this.bo = bo;
        }

        public String getController() {
            return controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getImpl() {
            return impl;
        }

        public void setImpl(String impl) {
            this.impl = impl;
        }

        public String getDao() {
            return dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

        public String getBo() {
            return bo;
        }

        public void setBo(String bo) {
            this.bo = bo;
        }
    }

}
