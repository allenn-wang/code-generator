package ${basePackageName}.${servicePackageName}.base;

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：base CRUD operation
*/
public interface Base${serviceClassName}<T extends Base${entityClassName}> {

    T selectByPrimaryKey(Object id);

    int deleteByPrimaryKey(Object id);

    int insert(T entity);

    int insertSelective(T entity);

    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeySelective(T entity);

    int batchInsert(List<T> entityList);
}