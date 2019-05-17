package com.allenn.generator.entity;

import java.io.Serializable;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class Configuration implements Serializable {
    private static final long serialVersionUID = 2821325309161293475L;

    private String author;
    private String packageName;
    private String basePackageName;
    private String autoDir;
    private Path path;
    private Db db;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBasePackageName() {
        return basePackageName == null ? "" : basePackageName + ".";
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public String getPackageName() {
        return packageName == null ? "" : packageName + ".";
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAutoDir() {
        return autoDir;
    }

    public void setAutoDir(String autoDir) {
        this.autoDir = autoDir;
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
        private String entity;
        private String mapper;

        public Path() {
        }

        public Path(String controller, String service, String impl, String dao, String entity, String mapper) {
            this.controller = controller;
            this.service = service;
            this.impl = impl;
            this.dao = dao;
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
