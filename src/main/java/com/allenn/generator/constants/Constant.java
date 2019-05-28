package com.allenn.generator.constants;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-21
 */
public interface Constant {
    interface TaskType {
        int BASE_ENTITY = 0;
        int BASE_DAO = 1;
        int BASE_CONTROLLER = 2;
        int BASE_SERVICE = 3;
        int BASE_SERVICE_IMPL = 4;
        int CONTROLLER = 5;
        int DAO = 6;
        int DTO = 7;
        int ENTITY = 8;
        int MAPPER = 9;
        int SERVICE = 10;
        int SERVICE_IMPL = 11;
        int EXCEPTION = 12;
        int EXCEPTION_HANDLER = 13;
        int RESULT_CODE_INTERFACE = 14;
        int RESULT_CODE = 15;
        int PAGE_OBJECT = 16;
        int RESULT_OBJECT = 17;
        int CRITERION = 18;
        int ENTITY_EXAMPLE = 19;
    }

    interface JavaType {
        String _INTEGER = "Integer";
        String _LONG = "Long";
        String _DOUBLE = "Double";
        String _FLOAT = "Float";
        String _STRING = "String";
        String _DATE = "Date";
        String _BYTE = "byte[]";
    }

    interface FtlTag {
        String AUTHOR = "author";
        String DATE = "date";
        String TABLE = "table";
        String BASE_PACKAGE_NAME = "basePackageName";
        String ROOT_PACKAGE_NAME = "rootPackageName";
        String CONTROLLER_PACKAGE_NAME = "controllerPackageName";
        String SERVICE_PACKAGE_NAME = "servicePackageName";
        String SERVICE_IMPL_PACKAGE_NAME = "serviceImplPackageName";
        String DAO_PACKAGE_NAME = "daoPackageName";
        String ENTITY_PACKAGE_NAME = "entityPackageName";
        String DTO_PACKAGE_NAME = "dtoPackageName";
        String CONTROLLER_CLASS_NAME = "controllerClassName";
        String SERVICE_CLASS_NAME = "serviceClassName";
        String SERVICE_IMPL_CLASS_NAME = "serviceImplClassName";
        String DAO_CLASS_NAME = "daoClassName";
        String ENTITY_CLASS_NAME = "entityClassName";
        String DTO_CLASS_NAME = "dtoClassName";

    }
}
