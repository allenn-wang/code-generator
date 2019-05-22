package com.allenn.generator.task;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.entity.Configuration;
import com.allenn.generator.entity.Table;
import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.utils.FileUtil;
import com.allenn.generator.utils.FreemarkerUtil;
import com.allenn.generator.utils.StringUtil;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class Task {
    private int taskType;
    private Table table;

    private String author;
    private String basePackageName;
    private String controllerPackageName;
    private String daoPackageName;
    private String dtoPackageName;
    private String servicePackageName;
    private String serviceImplPackageName;
    private String entityPackageName;
    private String mapperPackageName;

    public Task(int taskType) {
        this(taskType, null);
    }

    public Task(int taskType, Table table) {
        this.taskType = taskType;
        this.table = table;
        init();
    }

    private void init() {
        Configuration configuration = ConfigUtil.getConfiguration();
        this.author = configuration.getAuthor();
        this.basePackageName = configuration.getBasePackageName();
        this.controllerPackageName = configuration.getPath().getController();
        this.daoPackageName = configuration.getPath().getDao();
        this.dtoPackageName = configuration.getPath().getDto();
        this.servicePackageName = configuration.getPath().getService();
        this.serviceImplPackageName = configuration.getPath().getImpl();
        this.entityPackageName = configuration.getPath().getEntity();
        this.mapperPackageName = configuration.getPath().getMapper();
    }

    public void exec() {
        Map<String, Object> model = new HashMap<>();
        model.put(Constant.FtlTag.TABLE, table);
        model.put(Constant.FtlTag.AUTHOR, author);
        model.put(Constant.FtlTag.DATE, LocalDate.now());
        model.put(Constant.FtlTag.BASE_PACKAGE_NAME, basePackageName);
        model.put(Constant.FtlTag.CONTROLLER_PACKAGE_NAME, controllerPackageName);
        model.put(Constant.FtlTag.DAO_PACKAGE_NAME, daoPackageName);
        model.put(Constant.FtlTag.DTO_PACKAGE_NAME, dtoPackageName);
        model.put(Constant.FtlTag.SERVICE_PACKAGE_NAME, servicePackageName);
        model.put(Constant.FtlTag.SERVICE_IMPL_PACKAGE_NAME, serviceImplPackageName);
        model.put(Constant.FtlTag.ENTITY_PACKAGE_NAME, entityPackageName);

        File file = new File(getFileDir(taskType));
        if (FileUtil.openFile(file)) {
            try {
                Template template = FreemarkerUtil.getTemplate(taskType);
                StringWriter writer = new StringWriter();
                template.process(model, writer);
                FileUtil.generate2Java(file, writer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileDir(int taskType) {
        String baseJavaDir = basePackageName.replaceAll("\\.", File.separator);
        String javaBaseDir = FileUtil.getProjectTargetJavaPath();
        String resourceDir = FileUtil.getProjectTargetResourcePath();

        StringBuilder fileDirBuilder = new StringBuilder();
        switch (taskType) {
            case Constant.TaskType.MAPPER :
                fileDirBuilder.append(resourceDir).append(File.separator).append(mapperPackageName)
                    .append(File.separator).append(table.getClassName())
                    .append(StringUtil.upperFirstChar(mapperPackageName)).append(".xml");
            case Constant.TaskType.BASE_ENTITY :
                fileDirBuilder.append(javaBaseDir).append(File.separator).append(entityPackageName)
                        .append(File.separator).append("base").append(File.separator)
                        .append("Base").append(StringUtil.upperFirstChar(entityPackageName))
                        .append(".java");
            case Constant.TaskType.BASE_DAO :
                fileDirBuilder.append(javaBaseDir).append(File.separator).append(daoPackageName)
                        .append(File.separator).append("base").append(File.separator)
                        .append("Base").append(StringUtil.upperFirstChar(daoPackageName))
                        .append(".java");
            case Constant.TaskType.BASE_SERVICE :
                fileDirBuilder.append(javaBaseDir).append(File.separator).append(servicePackageName)
                        .append(File.separator).append("base").append(File.separator)
                        .append("Base").append(StringUtil.upperFirstChar(servicePackageName))
                        .append(".java");
            case Constant.TaskType.BASE_SERVICE_IMPL :
                fileDirBuilder.append(javaBaseDir).append(File.separator).append(servicePackageName)
                    .append(File.separator).append("base").append(File.separator)
                    .append("Base").append(StringUtil.upperFirstChar(servicePackageName))
                    .append(".java");
//            case Constant.TaskType.CONTROLLER :         return instance.getTemplate("Controller.ftl");
//            case Constant.TaskType.DAO :                return instance.getTemplate("Dao.ftl");
//            case Constant.TaskType.DTO:                 return instance.getTemplate("Domain.ftl");
//            case Constant.TaskType.ENTITY :             return instance.getTemplate("Entity.ftl");
//            case Constant.TaskType.SERVICE_IMPL :       return instance.getTemplate("ServiceImpl.ftl");
//            case Constant.TaskType.SERVICE :
                fileDirBuilder.append(javaBaseDir).append(File.separator).append(daoPackageName)
                        .append(File.separator).append("base").append(File.separator)
                        .append(table.getClassName()).append(StringUtil.upperFirstChar(daoPackageName))
                        .append(".java");
            default:                    return null;
        }
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getDtoPackageName() {
        return dtoPackageName;
    }

    public void setDtoPackageName(String dtoPackageName) {
        this.dtoPackageName = dtoPackageName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getServiceImplPackageName() {
        return serviceImplPackageName;
    }

    public void setServiceImplPackageName(String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }

    public String getEntityPackageName() {
        return entityPackageName;
    }

    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }
}
