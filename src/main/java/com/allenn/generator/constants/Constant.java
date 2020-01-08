package com.allenn.generator.constants;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public interface Constant {
    interface TaskType {
        int EXCEPTION = 1;
        int EXCEPTION_HANDLER = 2;
        int I_RESULT_CODE = 3;
        int RESULT_CODE = 4;
        int PAGE_OBJECT = 5;
        int RESULT_OBJECT = 6;
        int QUERY_OBJECT = 7;
        int QUERY_CONDITION = 8;
        int I_ENUM = 9;

        int BASE_ENTITY = 10;
        int BASE_DAO = 11;
        int BASE_CONTROLLER = 12;
        int BASE_SERVICE = 13;
        int BASE_SERVICE_IMPL = 14;

        int CONTROLLER = 15;
        int DAO = 16;
        int COMMON_MAPPER = 17;
        int MAPPER = 18;
        int SERVICE = 19;
        int SERVICE_IMPL = 20;
        int ENTITY = 21;
        int ENUM = 22;
        int BO = 23;
        int BASE_BO = 24;
    }

    interface JavaType {
        String _INTEGER = "Integer";
        String _LONG = "Long";
        String _DOUBLE = "Double";
        String _BIGDECIMAL = "BigDecimal";
        String _FLOAT = "Float";
        String _STRING = "String";
        String _DATE = "Date";
        String _BYTE = "byte[]";
    }

    interface FtlTag {
        String AUTHOR = "author";
        String DATE = "date";
        String TABLE = "table";
        String COMMON_PACKAGE_NAME = "commonPackageName";
        String BASE_PACKAGE_NAME = "basePackageName";
        String MODULE_PACKAGE_NAME = "modulePackageName";
        String ROOT_PACKAGE_NAME = "rootPackageName";
        String CORE_PACKAGE_NAME = "corePackageName";
        String CONTROLLER_PACKAGE_NAME = "controllerPackageName";
        String SERVICE_PACKAGE_NAME = "servicePackageName";
        String SERVICE_IMPL_PACKAGE_NAME = "serviceImplPackageName";
        String DAO_PACKAGE_NAME = "daoPackageName";
        String ENTITY_PACKAGE_NAME = "entityPackageName";
        String CONTROLLER_CLASS_NAME = "controllerClassName";
        String SERVICE_CLASS_NAME = "serviceClassName";
        String SERVICE_IMPL_CLASS_NAME = "serviceImplClassName";
        String DAO_CLASS_NAME = "daoClassName";
        String ENTITY_CLASS_NAME = "entityClassName";
        String SYSTEM_COLUMNS = "systemColumns";
        String ENUM_PACKAGE_NAME = "enumPackageName";
        String MODULE_NAME = "moduleName";
        String ENUM_CLASS_NAME= "enumClassName";
        String ENUM_ANNOTATION = "enumAnnotation";
        String ENUM_VALUE_TYPE = "enumValueType";
        String ENUM_VALUES = "enumValues";
        String CACHE_ENABLE = "cacheEnable";
        String SWAGGER_ENABLE = "swaggerEnable";
        String BO_PACKAGE_NAME = "boPackageName";
        String BO_CLASS_NAME = "boClassName";
    }

    interface FieldServiceType {
        String KEY = "key";             // 主键
        String STRING = "string";       // 字符串
        String INT = "int";             // 整数
        String NUMBER = "number";       // 小数
        String BOOLEAN = "boolean";     // 1-是 0-否
        String JOIN = "join";           // 外键
        String ENUM = "enum";           // 枚举
    }
}
