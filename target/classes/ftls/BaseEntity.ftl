package ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName};

import java.io.Serializable;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  base entity, default primaryKey
*/
public interface ${basePackageName?cap_first}${entityClassName} extends Serializable {
    void set_Id(Object id);

    Object get_Id();

    default void defaultAddValue() {
    }

    default void defaultUpdateValue() {
    }
}