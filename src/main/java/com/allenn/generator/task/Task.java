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
    private String basePackageName = "base";

    private int taskType;
    private Table table;

    private String author;
    private String rootPackageName;
    private String controllerPackageName;
    private String daoPackageName;
    private String dtoPackageName;
    private String servicePackageName;
    private String serviceImplPackageName;
    private String entityPackageName;
    private String mapperPackageName;

    private String controllerClassName;
    private String daoClassName;
    private String dtoClassName;
    private String serviceClassName;
    private String serviceImplClassName;
    private String entityClassName;
    private String mapperClassName;

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
        this.rootPackageName = configuration.getRootPackageName();
        this.controllerPackageName = configuration.getPath().getController();
        this.daoPackageName = configuration.getPath().getDao();
        this.dtoPackageName = configuration.getPath().getDto();
        this.servicePackageName = configuration.getPath().getService();
        this.serviceImplPackageName = configuration.getPath().getImpl();
        this.entityPackageName = configuration.getPath().getEntity();
        this.mapperPackageName = configuration.getPath().getMapper();

        this.controllerClassName = StringUtil.upperFirstChar(this.controllerPackageName.lastIndexOf(".") > 0
                ? this.controllerPackageName.substring(this.controllerPackageName.lastIndexOf(".") + 1)
                    : this.controllerPackageName);
        this.daoClassName = StringUtil.upperFirstChar(this.daoPackageName.lastIndexOf(".") > 0
                ? this.daoPackageName.substring(this.daoPackageName.lastIndexOf(".") + 1)
                    : this.daoPackageName);
        this.dtoClassName = StringUtil.upperFirstChar(this.dtoPackageName.lastIndexOf(".") > 0
                ? this.dtoPackageName.substring(this.dtoPackageName.lastIndexOf(".") + 1)
                    : this.dtoPackageName);
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
    }

    public void exec() {
        Map<String, Object> model = new HashMap<>();
        model.put(Constant.FtlTag.TABLE, table);
        model.put(Constant.FtlTag.AUTHOR, author);
        model.put(Constant.FtlTag.DATE, LocalDate.now());
        model.put(Constant.FtlTag.BASE_PACKAGE_NAME, basePackageName);
        model.put(Constant.FtlTag.ROOT_PACKAGE_NAME, rootPackageName);
        model.put(Constant.FtlTag.CONTROLLER_PACKAGE_NAME, controllerPackageName);
        model.put(Constant.FtlTag.DAO_PACKAGE_NAME, daoPackageName);
        model.put(Constant.FtlTag.DTO_PACKAGE_NAME, dtoPackageName);
        model.put(Constant.FtlTag.SERVICE_PACKAGE_NAME, servicePackageName);
        model.put(Constant.FtlTag.SERVICE_IMPL_PACKAGE_NAME, serviceImplPackageName);
        model.put(Constant.FtlTag.ENTITY_PACKAGE_NAME, entityPackageName);

        model.put(Constant.FtlTag.CONTROLLER_CLASS_NAME, controllerClassName);
        model.put(Constant.FtlTag.DAO_CLASS_NAME, daoClassName);
        model.put(Constant.FtlTag.DTO_CLASS_NAME, dtoClassName);
        model.put(Constant.FtlTag.SERVICE_CLASS_NAME, serviceClassName);
        model.put(Constant.FtlTag.SERVICE_IMPL_CLASS_NAME, serviceImplClassName);
        model.put(Constant.FtlTag.ENTITY_CLASS_NAME, entityClassName);


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
        String baseJavaPackage = StringUtil.package2Path(rootPackageName);
        String javaBaseDir = FileUtil.getProjectTargetJavaPath();
        String resourceDir = FileUtil.getProjectTargetResourcePath();

        StringBuilder fileDirBuilder = new StringBuilder();
        switch (taskType) {
            case Constant.TaskType.MAPPER :
                fileDirBuilder.append(resourceDir).append(File.separator)
                    .append(StringUtil.package2Path(mapperPackageName))
                    .append(File.separator).append(table.getClassName()).append(mapperClassName).append(".xml");
                break;
            case Constant.TaskType.BASE_ENTITY :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(entityPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(entityClassName).append(".java");
                break;
            case Constant.TaskType.BASE_DAO :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(daoPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(daoClassName).append(".java");
                break;
            case Constant.TaskType.BASE_SERVICE :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(servicePackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(serviceClassName).append(".java");
                break;
            case Constant.TaskType.BASE_SERVICE_IMPL :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(serviceImplPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(serviceClassName)
                        .append(serviceImplClassName).append(".java");
                break;
            case Constant.TaskType.BASE_CONTROLLER :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(controllerPackageName)).append(File.separator)
                        .append(StringUtil.upperFirstChar(basePackageName)).append(controllerClassName).append(".java");
                break;
            case Constant.TaskType.CONTROLLER :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(controllerPackageName))
                        .append(File.separator).append(table.getClassName()).append(controllerClassName).append(".java");
                break;
            case Constant.TaskType.DAO :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(daoPackageName))
                        .append(File.separator).append(table.getClassName()).append(daoClassName).append(".java");
                break;
            case Constant.TaskType.DTO:
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(dtoPackageName))
                        .append(File.separator).append(table.getClassName()).append(dtoClassName).append(".java");
                break;
            case Constant.TaskType.ENTITY :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(entityPackageName))
                        .append(File.separator).append(table.getClassName()).append(".java");
                break;
            case Constant.TaskType.SERVICE_IMPL :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(serviceImplPackageName))
                        .append(File.separator).append(table.getClassName()).append(serviceClassName)
                        .append(serviceImplClassName).append(".java");
                break;
            case Constant.TaskType.SERVICE :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(servicePackageName))
                        .append(File.separator).append(table.getClassName()).append(serviceClassName).append(".java");
                break;
            case Constant.TaskType.EXCEPTION :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append("exception").append(File.separator).append("BusinessException.java");
                break;
            case Constant.TaskType.EXCEPTION_HANDLER :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                    .append(baseJavaPackage).append(File.separator)
                    .append(basePackageName).append(File.separator)
                    .append("exception").append(File.separator).append("BusinessExceptionHandler.java");
                break;
            case Constant.TaskType.RESULT_CODE_INTERFACE :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append("exception").append(File.separator).append("IResultCode.java");
                break;
            case Constant.TaskType.RESULT_CODE :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append("exception").append(File.separator).append("ResultCode.java");
                break;
            case Constant.TaskType.PAGE_OBJECT :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append("common").append(File.separator).append("PageObject.java");
                break;
            case Constant.TaskType.RESULT_OBJECT :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append("common").append(File.separator).append("ResultObject.java");
                break;
            case Constant.TaskType.CRITERION :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(basePackageName).append(File.separator)
                        .append(StringUtil.package2Path(entityPackageName)).append(File.separator).append("Criterion.java");
                break;
            case Constant.TaskType.ENTITY_EXAMPLE :
                fileDirBuilder.append(javaBaseDir).append(File.separator)
                        .append(baseJavaPackage).append(File.separator)
                        .append(StringUtil.package2Path(entityPackageName))
                        .append(File.separator).append(table.getClassName()).append("Example.java");
                break;
        }

        return fileDirBuilder.toString();
    }
}
