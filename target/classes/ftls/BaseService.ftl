package ${rootPackageName}.${commonPackageName}.${basePackageName}.${servicePackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};

import java.util.List;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  base CRUD operation
*/
public interface ${basePackageName?cap_first}${serviceClassName}<T extends ${basePackageName?cap_first}${entityClassName}, K extends Object> {

    T selectByPrimaryKey(K id);

    int deleteByPrimaryKey(K id);

    int deleteBySelective(T entity);

    int deleteSoftBySelective(T entity);

    int insert(T entity);

    int insertSelective(T entity);

    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeySelective(T entity);

    int batchInsert(List<T> entityList);

    int batchUpdateSelective(List<T> entityList);

    int batchUpdate(List<T> entityList);
}