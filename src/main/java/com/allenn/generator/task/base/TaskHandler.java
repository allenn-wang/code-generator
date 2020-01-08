package com.allenn.generator.task.base;

import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.utils.FileUtil;
import com.allenn.generator.utils.FreemarkerUtil;
import com.allenn.generator.utils.StringUtil;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public abstract class TaskHandler implements Task {
    protected String commonPackageName = "common";

    protected String basePackageName = "base";

    protected String modulePackageName = "module";

    protected String corePackageName = "core";

    protected String enumPackageName = "enums";

    protected int taskType;

    // 框架基础包路径
    // eg. BaseDao路径 <rootPackageName> + <basePackageName> + BaseDao.java
    protected String rootPackageName;

    // 业务代码生成目标模块名
    // 业务代码会生成至此模块java目录下
    // 配置为空或者src, 会生成至code-generator项目target目录下
    protected String coreProjectName;

    // 实体模型类生成目标模块名
    // 实体模型类整个项目共用(entity, dto)
    // 配置为空或者src, 会生成至code-generator项目target目录下
    protected String commonProjectName;

    // 实体模型类生成模块名
    // eg. entity路径 <rootPackageName> + <commonPackageName> + <basePackageName>
    //          + <moduleName> + <entityPackageName> + <table.className><entityClassName>.java
    protected String moduleName;

    // 注释 author
    protected String author;

    // 是否使用缓存
    protected String cacheEnable;

    // 是否生成swagger注解
    protected String swaggerEnable;

    public TaskHandler(int taskType) {
        this.taskType = taskType;
        com.allenn.generator.entity.Configuration configuration = ConfigUtil.getConfiguration();
        this.moduleName = configuration.getModuleName();
        this.rootPackageName = configuration.getRootPackageName();
        this.commonProjectName = configuration.getCommonProjectName();
        this.coreProjectName = configuration.getCoreProjectName();
        this.author = configuration.getAuthor();
        this.cacheEnable = configuration.getCacheEnable();
        this.swaggerEnable = configuration.getSwaggerEnable();
    }

    @Override
    public void exec() {
        Map<String, Object> model = buildModel();
        String fileDir = getFileDir();
        writeFile(new File(fileDir), model);
    }

    public abstract Map<String, Object> buildModel();
    public abstract String getFileDir();

    private void writeFile(File file, Map<String, Object> model) {
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

    public String getCommonProjectJavaDir () {
        StringBuilder fileDirBuilder = new StringBuilder();
        fileDirBuilder.append(getProjectJavaBaseDir(commonProjectName))
                .append(File.separator).append(StringUtil.package2Path(rootPackageName))
                .append(File.separator).append(StringUtil.package2Path(commonPackageName));
        return fileDirBuilder.toString();
    }

    public String getProjectJavaBaseDir(String projectName) {
        return getProjectBaseDir("java", projectName);
    }

    public String getProjectResourcesDir(String projectName) {
        return getProjectBaseDir("resources", projectName);
    }

    private String getProjectBaseDir(String type, String projectName) {
        String projectBaseDir = null;
        if ("java".equals(type)) {
            projectBaseDir = FileUtil.getProjectTargetJavaPath();
        } else if ("resources".equals(type)) {
            projectBaseDir = FileUtil.getProjectTargetResourcePath();
        }
        if (projectName != null && projectName.trim().length() > 0
                && !"src".equals(projectName)) {
            projectBaseDir = projectBaseDir.replace("/tool-code-generator/target/", "/" + projectName + "/");
        }
        return projectBaseDir;
    }
}
