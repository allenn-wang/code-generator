package ${rootPackageName}.${basePackageName}.${serviceImplPackageName};

import ${rootPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};
import ${rootPackageName}.${basePackageName}.${servicePackageName}.${basePackageName?cap_first}${serviceClassName};
import ${rootPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
*/
public abstract class ${basePackageName?cap_first}${serviceClassName}${serviceImplClassName}<T extends ${basePackageName?cap_first}${entityClassName}, K extends Object> implements ${basePackageName?cap_first}${serviceClassName}<T, K> {
    public abstract ${basePackageName?cap_first}${daoClassName}<T, K> get${basePackageName?cap_first}${daoClassName}();

    @Transactional(rollbackFor = Throwable.class)
    public T selectByPrimaryKey(K id) {
        return get${basePackageName?cap_first}${daoClassName}().selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int deleteByPrimaryKey(K id) {
        return get${basePackageName?cap_first}${daoClassName}().deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int insert(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().insert(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int insertSelective(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().insertSelective(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int updateByPrimaryKey(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().updateByPrimaryKey(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int updateByPrimaryKeySelective(T entity) {
        return get${basePackageName?cap_first}${daoClassName}().updateByPrimaryKeySelective(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int batchInsert(List<T> entityList) {
        return get${basePackageName?cap_first}${daoClassName}().batchInsert(entityList);
    }
}