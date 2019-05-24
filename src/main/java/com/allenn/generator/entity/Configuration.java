package com.allenn.generator.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Description  code-generator\src\main\resources\generator.yaml
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class Configuration implements Serializable {
    private static final long serialVersionUID = 2821325309161293475L;
    // database instance name
    private String dataBaseName;
    // to generate javadoc for author
    private String author;
    // code's root package, e.g. the service code path: package + "." + path.service
    private String rootPackageName;
    // the mvc module to generate
    private Path path;
    // database connection info
    private Db db;

    public void vaildConfiguration() throws Exception {
        if (StringUtils.isEmpty(this.rootPackageName)) {
            throw new Exception("Expect package's name, but get a blank String.");
        }

        if (StringUtils.isEmpty(this.dataBaseName)) {
            throw new Exception("Expect dataBase's name, but get a blank String.");
        }

        if (StringUtils.isEmpty(this.path.getDao())) {
            this.path.dao = "dao";
            System.out.println("Use default value 'dao' for path.dao, because of unknown configuration.");
        }

        if (StringUtils.isEmpty(this.path.getController())) {
            this.path.controller = "controller";
            System.out.println("Use default value 'controller' for path.controller, because of unknown configuration.");
        }

        if (StringUtils.isEmpty(this.path.getService())) {
            this.path.service = "service";
            System.out.println("Use default value 'service' for path.service, because of unknown configuration.");
        }

        if (StringUtils.isEmpty(this.path.getImpl())) {
            this.path.impl = "service.impl";
            System.out.println("Use default value 'service.impl' for path.impl, because of unknown configuration.");
        }

        if (StringUtils.isEmpty(this.path.getDto())) {
            this.path.dto = "dto";
            System.out.println("Use default value 'dto' for path.domain, because of unknown configuration.");
        }

        if (StringUtils.isEmpty(this.path.getEntity())) {
            this.path.entity = "entity";
            System.out.println("Use default value 'entity' for path.entity, because of unknown configuration.");
        }

        if (StringUtils.isEmpty(this.path.getMapper())) {
            this.path.mapper = "mapper";
            System.out.println("Use default value 'mapper' for path.mapper, because of unknown configuration.");
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

    public String getRootPackageName() {
        return rootPackageName;
    }

    public void setRootPackageName(String rootPackageName) {
        this.rootPackageName = rootPackageName;
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
        private String controller;
        private String service;
        private String impl;
        private String dao;
        private String dto;
        private String entity;
        private String mapper;

        public Path() {
        }

        public Path(String controller, String service, String impl, String dao,
                    String dto, String entity, String mapper) {
            this.controller = controller;
            this.service = service;
            this.impl = impl;
            this.dao = dao;
            this.dto = dto;
            this.entity = entity;
            this.mapper = mapper;
        }

        public String getController() {
            return controller == null ? "" : controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service == null ? "" : service;
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
            return dao == null ? "" : dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getDto() {
            return dto;
        }

        public void setDto(String dto) {
            this.dto = dto;
        }

        public String getEntity() {
            return entity == null ? "" : entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper == null ? "" : mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

    }

}
