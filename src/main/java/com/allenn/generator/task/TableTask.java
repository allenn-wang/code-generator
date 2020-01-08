package com.allenn.generator.task;

import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.utils.StringUtil;
import com.allenn.generator.task.base.Task;
import com.allenn.generator.task.base.TaskHandler;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class TableTask extends TaskHandler implements Task {

    // 数据库表
    private com.allenn.generator.entity.Table table;

    // 业务代码controller层包路径  path.controller
    private String controllerPackageName;

    // 业务代码dao层包路径   path.dao
    private String daoPackageName;

    // 业务代码service层包路径   path.service
    private String servicePackageName;

    // 业务代码service impl层包路径   path.impl
    private String serviceImplPackageName;

    // 业务代码entity层包路径   path.entity
    private String entityPackageName;

    // mapper xml文件路径   path.mapper
    // 生成在resources目录下
    private String mapperPackageName;

    // 业务代码bo层包路径   path.bo
    private String boPackageName;

    // controller类结尾名  controllerPackageName截取最后'.'点号后首字母大写
    private String controllerClassName;

    // dao类结尾名  daoPackageName截取最后'.'点号后首字母大写
    private String daoClassName;

    // service类结尾名  servicePackageName截取最后'.'点号后首字母大写
    private String serviceClassName;

    // serviceImpl类结尾名  serviceImplPackageName截取最后'.'点号后首字母大写
    private String serviceImplClassName;

    // entity类结尾名  entityPackageName截取最后'.'点号后首字母大写
    private String entityClassName;

    // mapper xml结尾名  mapperPackageName截取最后'.'点号后首字母大写
    private String mapperClassName;

    // bo类结尾名  boPackageName截取最后'.'点号后首字母大写
    private String boClassName;

    // 所有表业务系统字段名
    private List<String> systemColumns;

    public TableTask(int taskType) {
        super(taskType);
        init();
    }

    public TableTask(int taskType, com.allenn.generator.entity.Table table) {
        super(taskType);
        this.table = table;
        init();
    }

    private void init() {
        com.allenn.generator.entity.Configuration configuration = ConfigUtil.getConfiguration();
        this.coreProjectName = configuration.getCoreProjectName();
        this.moduleName = configuration.getModuleName();
        this.rootPackageName = configuration.getRootPackageName();
        this.controllerPackageName = configuration.getPath().getController();
        this.daoPackageName = configuration.getPath().getDao();
        this.servicePackageName = configuration.getPath().getService();
        this.serviceImplPackageName = configuration.getPath().getImpl();
        this.entityPackageName = configuration.getPath().getEntity();
        this.mapperPackageName = configuration.getPath().getMapper();
        this.boPackageName = configuration.getPath().getBo();
        this.systemColumns = configuration.getSystemColumns();

        this.controllerClassName = StringUtil.upperFirstChar(this.controllerPackageName.lastIndexOf(".") > 0
                ? this.controllerPackageName.substring(this.controllerPackageName.lastIndexOf(".") + 1)
                : this.controllerPackageName);
        this.daoClassName = StringUtil.upperFirstChar(this.daoPackageName.lastIndexOf(".") > 0
                ? this.daoPackageName.substring(this.daoPackageName.lastIndexOf(".") + 1)
                : this.daoPackageName);
        this.serviceClassName = StringUtil.upperFirstChar(this.servicePackageName.lastIndexOf(".") > 0
                ? this.servicePackageName.substring(this.servicePackageName.lastIndexOf(".") + 1)
                : this.servicePackageName);
        this.serviceImplClassName = StringUtil.upperFirstChar(this.serviceImplPackageName.lastIndexOf(".") > 0
                ? this.serviceImplPackageName.substring(this.serviceImplPackageName.lastIndexOf(".") + 1)
                : this.serviceImplPackageName);
        this.entityClassName = StringUtil.upperFirstChar(this.entityPackageName.lastIndexOf(".") > 0
                ? this.entityPackageName.substring(this.entityPackageName.lastIndexOf(".") + 1)
                : this.entityPackageName);
        this.mapperClassName = StringUtil.upperFirstChar(this.mapperPackageName.lastIndexOf(".") > 0
                ? this.mapperPackageName.substring(this.mapperPackageName.lastIndexOf(".") + 1)
                : this.mapperPackageName);
        this.boClassName = StringUtil.upperFirstChar(this.boPackageName.lastIndexOf(".") > 0
                ? this.boPackageName.substring(this.boPackageName.lastIndexOf(".") + 1)
                : this.boPackageName);
    }

    public Map<String, Object> buildModel() {
        Map<String, Object> model = new HashMap<>();
        model.put(com.allenn.generator.constants.Constant.FtlTag.TABLE, table);
        model.put(com.allenn.generator.constants.Constant.FtlTag.AUTHOR, author);
        model.put(com.allenn.generator.constants.Constant.FtlTag.DATE, LocalDate.now());
        model.put(com.allenn.generator.constants.Constant.FtlTag.BASE_PACKAGE_NAME, basePackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ROOT_PACKAGE_NAME, rootPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.COMMON_PACKAGE_NAME, commonPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.MODULE_PACKAGE_NAME, modulePackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.CORE_PACKAGE_NAME, corePackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENUM_PACKAGE_NAME, enumPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.CONTROLLER_PACKAGE_NAME, controllerPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.DAO_PACKAGE_NAME, daoPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.SERVICE_PACKAGE_NAME, servicePackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.SERVICE_IMPL_PACKAGE_NAME, serviceImplPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENTITY_PACKAGE_NAME, entityPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.BO_PACKAGE_NAME, boPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.BO_CLASS_NAME, boClassName);

        model.put(com.allenn.generator.constants.Constant.FtlTag.CONTROLLER_CLASS_NAME, controllerClassName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.DAO_CLASS_NAME, daoClassName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.SERVICE_CLASS_NAME, serviceClassName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.SERVICE_IMPL_CLASS_NAME, serviceImplClassName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENTITY_CLASS_NAME, entityClassName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.SYSTEM_COLUMNS, systemColumns);
        model.put(com.allenn.generator.constants.Constant.FtlTag.MODULE_NAME, moduleName);

        model.put(com.allenn.generator.constants.Constant.FtlTag.CACHE_ENABLE, cacheEnable);
        model.put(com.allenn.generator.constants.Constant.FtlTag.SWAGGER_ENABLE, swaggerEnable);

        return model;
    }

    public String getFileDir() {
        return getFileDir(this.taskType);
    }

    private String getFileDir(int taskType) {
        String commonProjectJavaDir = getCommonProjectJavaDir();
        String coreProjectJavaBaseDir = getProjectJavaBaseDir(coreProjectName);
        String coreResourcesDir = getProjectResourcesDir(coreProjectName);
        String coreProjectJavaDir = coreProjectJavaBaseDir + StringUtil.package2Path(rootPackageName);
        StringBuilder fileDirBuilder = new StringBuilder();
        switch (taskType) {
            case com.allenn.generator.constants.Constant.TaskType.BASE_ENTITY:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(entityPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(entityClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.BASE_BO:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(boPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(boClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.BASE_DAO:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(daoPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(daoClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.BASE_SERVICE:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(servicePackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(serviceClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.BASE_SERVICE_IMPL:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(serviceImplPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(serviceClassName)
                        .append(serviceImplClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.BASE_CONTROLLER:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(controllerPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(controllerClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.ENTITY:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(modulePackageName).append(File.separator)
                        .append(moduleName).append(File.separator)
                        .append(StringUtil.package2Path(entityPackageName)).append(File.separator)
                        .append(table.getClassName()).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.BO:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(modulePackageName).append(File.separator)
                        .append(moduleName).append(File.separator)
                        .append(StringUtil.package2Path(boPackageName)).append(File.separator)
                        .append(table.getClassName()).append(boClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.EXCEPTION:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("BusinessException.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.EXCEPTION_HANDLER:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("BusinessExceptionHandler.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.I_RESULT_CODE:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("IResultCode.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.RESULT_CODE:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("ResultCode.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.PAGE_OBJECT:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("PageObject.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.RESULT_OBJECT:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("ResultObject.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.QUERY_OBJECT:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("QueryObject.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.QUERY_CONDITION:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(basePackageName).append(File.separator).append("QueryCondition.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.I_ENUM:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(enumPackageName).append(File.separator).append("IEnum.java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.ENUM:
                fileDirBuilder.append(commonProjectJavaDir).append(File.separator)
                        .append(StringUtil.package2Path(mapperPackageName))
                        .append(File.separator).append(table.getClassName()).append("Enum.xml");
                break;
            case com.allenn.generator.constants.Constant.TaskType.CONTROLLER:
                fileDirBuilder.append(coreProjectJavaDir).append(File.separator)
                        .append(moduleName).append(File.separator)
                        .append(corePackageName).append(File.separator)
                        .append(StringUtil.package2Path(controllerPackageName))
                        .append(File.separator).append(table.getClassName()).append(controllerClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.DAO:
                fileDirBuilder.append(coreProjectJavaDir).append(File.separator)
                        .append(moduleName).append(File.separator)
                        .append(corePackageName).append(File.separator)
                        .append(StringUtil.package2Path(daoPackageName))
                        .append(File.separator).append(table.getClassName()).append(daoClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.SERVICE_IMPL:
                fileDirBuilder.append(coreProjectJavaDir).append(File.separator)
                        .append(moduleName).append(File.separator)
                        .append(corePackageName).append(File.separator)
                        .append(StringUtil.package2Path(serviceImplPackageName))
                        .append(File.separator).append(table.getClassName()).append(serviceClassName)
                        .append(serviceImplClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.SERVICE:
                fileDirBuilder.append(coreProjectJavaDir).append(File.separator)
                        .append(moduleName).append(File.separator)
                        .append(corePackageName).append(File.separator)
                        .append(StringUtil.package2Path(servicePackageName))
                        .append(File.separator).append(table.getClassName()).append(serviceClassName).append(".java");
                break;
            case com.allenn.generator.constants.Constant.TaskType.MAPPER:
                fileDirBuilder.append(coreResourcesDir).append(File.separator)
                        .append(StringUtil.package2Path(mapperPackageName))
                        .append(File.separator).append(table.getClassName()).append(mapperClassName).append(".xml");
                break;
            case com.allenn.generator.constants.Constant.TaskType.COMMON_MAPPER:
                fileDirBuilder.append(coreResourcesDir).append(File.separator)
                        .append(StringUtil.package2Path(mapperPackageName))
                        .append(File.separator).append(table.getClassName()).append("Common.xml");
                break;
        }
        return fileDirBuilder.toString();
    }
}
