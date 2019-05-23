package ${basePackageName}.${daoPackageName}.base;

import ${basePackageName}.${entityPackageName}.base.Base${entityClassName};

import java.util.List;

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：base CRUD operation
*/
public interface Base${daoClassName}<T extends Base${entityClassName}> {
    T selectByPrimaryKey(Object id);

    int deleteByPrimaryKey(Object id);

    int insert(T entity);

    int insertSelective(T entity);

    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeySelective(T entity);

    int batchInsert(List<T> entityList);
}