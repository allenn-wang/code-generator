package ${rootPackageName}.${basePackageName}.${serviceImplPackageName};

import ${rootPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};
import ${rootPackageName}.${basePackageName}.${servicePackageName}.${basePackageName?cap_first}${serviceClassName};
import ${rootPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};

import java.util.List;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
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
}