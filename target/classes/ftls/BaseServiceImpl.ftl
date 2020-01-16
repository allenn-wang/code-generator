package ${rootPackageName}.${commonPackageName}.${basePackageName}.${serviceImplPackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.${servicePackageName}.${basePackageName?cap_first}${serviceClassName};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};

import java.util.List;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:
*/
public abstract class ${basePackageName?cap_first}${serviceClassName}${serviceImplClassName}<T extends ${basePackageName?cap_first}${entityClassName}, K extends Object> implements ${basePackageName?cap_first}${serviceClassName}<T, K> {
    public abstract ${basePackageName?cap_first}${daoClassName}<T, K> get${basePackageName?cap_first}${daoClassName}();

    @Override
    public T selectByPrimaryKey(K id) {
        return get${basePackageName?cap_first}${daoClassName}().selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(K id) {
        return get${basePackageName?cap_first}${daoClassName}().deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBySelective(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().deleteBySelective(entity);
    }

    @Override
    public int deleteSoftBySelective(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().deleteSoftBySelective(entity);
    }

    @Override
    public int insert(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().insertSelective(entity);
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().updateByPrimaryKeySelective(entity);
    }

    @Override
    public int batchInsert(List<T> entityList) {
        return get${basePackageName?cap_first}${daoClassName}().batchInsert(entityList);
    }

    @Override
    public int batchUpdate(List<T> entityList) {
        return get${basePackageName?cap_first}${daoClassName}().batchUpdate(entityList);
    }

    protected void defaultQueryObject(QueryObject queryObject) {
        if (null == queryObject) {
            queryObject = new QueryObject();
        }
        queryObject.addNotDeleteCondition();
    }
}