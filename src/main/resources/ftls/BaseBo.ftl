package ${rootPackageName}.${commonPackageName}.${basePackageName}.${boPackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};

import java.io.Serializable;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  base bo
*/
public interface ${basePackageName?cap_first}${boClassName}<E extends ${basePackageName?cap_first}${entityClassName}> extends Serializable {
    E convert();

    void build(E entity);
}