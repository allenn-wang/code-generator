package ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName};

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  base entity, default primaryKey
*/
@Slf4j
public interface ${basePackageName?cap_first}${entityClassName} extends Serializable {
    void set_Id(Object id);

    Object get_Id();

    default void defaultAddValue() {
        try {
            Class entity = this.getClass();
            entity.getMethod("setIsDelete", Integer.class).invoke(this, 0);
            entity.getMethod("setIsInner", Integer.class).invoke(this, 0);
            entity.getMethod("setAddUser", String.class).invoke(this, "");
            entity.getMethod("setAddTime", Date.class).invoke(this, new Date());
        } catch (Exception e) {
            log.warn("defaultAddValue err.", e);
        }
    }

    default void defaultUpdateValue() {
        try {
            Class entity = this.getClass();
            entity.getMethod("setUpdateUser", String.class).invoke(this, "");
            entity.getMethod("setUpdateTime", Date.class).invoke(this, new Date());
        } catch (Exception e) {
            log.warn("defaultUpdateValue err.", e);
        }
    }

    default void setDelete(Object id) {
        set_Id(id);
        try {
            Class entity = this.getClass();
            entity.getMethod("setIsDelete", Integer.class).invoke(this, 1);
            entity.getMethod("setUpdateUser", String.class).invoke(this, "");
            entity.getMethod("setUpdateTime", Date.class).invoke(this, new Date());
        } catch (Exception e) {
            log.warn("setDelete err.", e);
        }
    }
}