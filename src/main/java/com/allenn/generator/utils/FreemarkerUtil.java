package com.allenn.generator.utils;

import com.allenn.generator.constants.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-20
 */
public class FreemarkerUtil {
    private static Configuration instance;
    static {
        instance = new Configuration(Configuration.VERSION_2_3_23);
        try {
            instance.setDirectoryForTemplateLoading(new File(FreemarkerUtil.class.getClassLoader().getResource("ftls").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance.setEncoding(Locale.CHINA, "utf-8");
    }

    public static Template getTemplate(int templateType) throws Exception {
        switch (templateType) {
            case Constant.TaskType.BASE_ENTITY :            return instance.getTemplate("BaseEntity.ftl");
            case Constant.TaskType.BASE_DAO :               return instance.getTemplate("BaseDao.ftl");
            case Constant.TaskType.BASE_SERVICE :           return instance.getTemplate("BaseService.ftl");
            case Constant.TaskType.BASE_SERVICE_IMPL :      return instance.getTemplate("BaseServiceImpl.ftl");
            case Constant.TaskType.BASE_CONTROLLER :        return instance.getTemplate("BaseController.ftl");
            case Constant.TaskType.CONTROLLER :             return instance.getTemplate("Controller.ftl");
            case Constant.TaskType.DAO :                    return instance.getTemplate("Dao.ftl");
            case Constant.TaskType.DTO:                     return instance.getTemplate("Dto.ftl");
            case Constant.TaskType.ENTITY :                 return instance.getTemplate("Entity.ftl");
            case Constant.TaskType.SERVICE_IMPL :           return instance.getTemplate("ServiceImpl.ftl");
            case Constant.TaskType.MAPPER :                 return instance.getTemplate("Mapper.ftl");
            case Constant.TaskType.SERVICE :                return instance.getTemplate("Service.ftl");
            case Constant.TaskType.EXCEPTION :              return instance.getTemplate("BusinessException.ftl");
            case Constant.TaskType.EXCEPTION_HANDLER :      return instance.getTemplate("BusinessExceptionHandler.ftl");
            case Constant.TaskType.RESULT_CODE_INTERFACE :  return instance.getTemplate("IResultCode.ftl");
            case Constant.TaskType.RESULT_CODE :            return instance.getTemplate("ResultCode.ftl");
            case Constant.TaskType.PAGE_OBJECT :            return instance.getTemplate("PageObject.ftl");
            case Constant.TaskType.RESULT_OBJECT :          return instance.getTemplate("ResultObject.ftl");
            default:                    return null;
        }
    }
}
