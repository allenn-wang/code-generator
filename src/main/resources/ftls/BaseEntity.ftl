package ${rootPackageName}.${basePackageName}.${entityPackageName};

import java.io.Serializable;

/**
* @author：  ${author}
* @date：    ${date}
* @description：  base entity, default primaryKey
*/
public interface ${basePackageName?cap_first}${entityClassName} extends Serializable {
    public void set_Id(Object id);

    public Object get_Id();
}