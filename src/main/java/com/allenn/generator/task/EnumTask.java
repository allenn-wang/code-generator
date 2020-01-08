package com.allenn.generator.task;

import com.allenn.generator.utils.StringUtil;
import com.allenn.generator.task.base.Task;
import com.allenn.generator.task.base.TaskHandler;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class EnumTask extends TaskHandler implements Task {

    // 枚举类名  tableClassName + columnClassName
    private String enumClassName;

    // 枚举类注释
    private String enumAnnotation;

    // 枚举值类型  string int
    private String enumValueType;

    // 枚举值
    private Map<String, String> enumValues;


    public EnumTask(com.allenn.generator.entity.Table table, com.allenn.generator.entity.Column column) {
        super(com.allenn.generator.constants.Constant.TaskType.ENUM);
        init(table, column);
    }

    private void init(com.allenn.generator.entity.Table table, com.allenn.generator.entity.Column column) {
        this.enumClassName = table.getClassName() + StringUtil.upperFirstChar(column.getPropertyName());
        this.enumAnnotation = column.getAnnotation();
        this.enumValueType = column.getJavaType();
        this.enumValues = column.getCommentOption().getAllowValues();
    }

    public Map<String, Object> buildModel() {
        Map<String, Object> model = new HashMap<>();
        model.put(com.allenn.generator.constants.Constant.FtlTag.ROOT_PACKAGE_NAME, rootPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.COMMON_PACKAGE_NAME, commonPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.BASE_PACKAGE_NAME, basePackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENUM_PACKAGE_NAME, enumPackageName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.MODULE_NAME, moduleName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.AUTHOR, this.author);
        model.put(com.allenn.generator.constants.Constant.FtlTag.DATE, LocalDate.now());
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENUM_CLASS_NAME, this.enumClassName);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENUM_ANNOTATION, this.enumAnnotation);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENUM_VALUE_TYPE, this.enumValueType);
        model.put(com.allenn.generator.constants.Constant.FtlTag.ENUM_VALUES, this.enumValues);
        return model;
    }

    public String getFileDir() {
        StringBuilder fileDirBuilder = new StringBuilder();
        fileDirBuilder.append(getCommonProjectJavaDir())
                .append(File.separator).append(StringUtil.package2Path(enumPackageName))
                .append(File.separator).append(StringUtil.package2Path(moduleName))
                .append(File.separator).append(enumClassName).append(".java");
        return fileDirBuilder.toString();
    }
}
