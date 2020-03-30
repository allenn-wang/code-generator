package ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName};

import java.io.Serializable;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  base pojo
*/
public interface ${basePackageName?cap_first}Pojo<E extends ${basePackageName?cap_first}${entityClassName}> extends Serializable {
    E convert();

    void build(E entity);
}