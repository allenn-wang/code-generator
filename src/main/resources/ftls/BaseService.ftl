package ${rootPackageName}.${basePackageName}.${servicePackageName};

import ${rootPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};

import java.util.List;

/**
* @author：  ${author}
* @date：    ${date}
* @description：  base CRUD operation
*/
public interface ${basePackageName?cap_first}${serviceClassName}<T extends ${basePackageName?cap_first}${entityClassName}, K extends Object> {

    T selectByPrimaryKey(K id);

    int deleteByPrimaryKey(K id);

    int insert(T entity);

    int insertSelective(T entity);

    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeySelective(T entity);

    int batchInsert(List<T> entityList);
}