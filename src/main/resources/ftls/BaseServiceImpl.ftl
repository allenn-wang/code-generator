package ${basePackageName}.${servicePackageName}.base;

import ${basePackageName}.${entityPackageName}.base.Base${entityClassName};
import ${basePackageName}.${servicePackageName}.base.Base${serviceClassName};

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：
*/
public abstract class Base${serviceClassName}${serviceImplClassName}<T extends Base${entityClassName}> implements Base${serviceClassName}<T> {
    public abstract Base${daoClassName}<T> getBase${daoClassName}();

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public T selectByPrimaryKey(Object id) {
        return getBase${daoClassName}().selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteByPrimaryKey(Object id) {
        return getBase${daoClassName}().deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int insert(T entity) {
        return getBase${daoClassName}().insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int insertSelective(T entity) {
        return getBase${daoClassName}().insertSelective(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int updateByPrimaryKey(T entity) {
        return getBase${daoClassName}().updateByPrimaryKey(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int updateByPrimaryKeySelective(T entity) {
        return getBase${daoClassName}().updateByPrimaryKeySelective(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void batchInsert(List<T> entityList) {
        getBase${daoClassName}().batchInsert(entityList);
    }
}