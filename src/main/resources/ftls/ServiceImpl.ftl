package ${rootPackageName}.${moduleName}.${corePackageName}.${serviceImplPackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.${serviceImplPackageName}.${basePackageName?cap_first}${serviceClassName}${serviceImplClassName};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.PageObject;
import ${rootPackageName}.${moduleName}.${corePackageName}.${daoPackageName}.${table.className}${daoClassName};
import ${rootPackageName}.${moduleName}.${corePackageName}.${servicePackageName}.${table.className}${serviceClassName};
import ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${entityPackageName}.${table.className};
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
import ${rootPackageName}.${commonPackageName}.${basePackageName}.redis.RedisCache;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.redis.schema.RedisSchema;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.redis.schema.base.RedisCacheOperate;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.redis.schema.base.RedisDataType;
</#if>

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
import java.util.concurrent.TimeUnit;
</#if>

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  ${table.annotation}
*/
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class ${table.className}${serviceClassName}${serviceImplClassName} extends ${basePackageName?cap_first}${serviceClassName}${serviceImplClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> implements ${table.className}${serviceClassName} {

    @Autowired
	protected ${table.className}${serviceClassName} ${table.className?uncap_first}${serviceClassName};

	@Autowired
	protected ${table.className}${daoClassName} ${table.className?uncap_first}${daoClassName};

	@Override
	public Base${daoClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> get${basePackageName?cap_first}${daoClassName}() {
		return this.${table.className?uncap_first}${daoClassName};
	}

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.INSERT,
        dataType = RedisDataType.LIST, argName = "${table.className?uncap_first}List", foreignKeys = "<#list table.foreignKeyCols as item>#entity.${item.propertyName}<#if item_has_next>,</#if></#list>")
</#if>
    public int batchInsert(List<${table.className}> ${table.className?uncap_first}List) {
        return super.batchInsert(${table.className?uncap_first}List);
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.UPDATE,
    dataType = RedisDataType.LIST, argName = "${table.className?uncap_first}List", foreignKeys = "<#list table.foreignKeyCols as item>#entity.${item.propertyName}<#if item_has_next>,</#if></#list>")
</#if>
    public int batchUpdateSelective(List<${table.className}> ${table.className?uncap_first}List) {
        return super.batchUpdateSelective(${table.className?uncap_first}List);
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.UPDATE,
        dataType = RedisDataType.LIST, argName = "${table.className?uncap_first}List", foreignKeys = "<#list table.foreignKeyCols as item>#entity.${item.propertyName}<#if item_has_next>,</#if></#list>")
</#if>
    public int batchUpdate(List<${table.className}> ${table.className?uncap_first}List) {
        return super.batchUpdate(${table.className?uncap_first}List);
    }

    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.SELECT,
        dataType = RedisDataType.OBJECT, argName = "${table.primaryKeyColumn.propertyName}", expire = ${table.commentOption.singleCacheExpire}, timeUnit = TimeUnit.${table.commentOption.singleCacheUnit})
    </#if>
    public ${table.className} selectByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
        return super.selectByPrimaryKey(${table.primaryKeyColumn.propertyName});
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.DELETE,
        dataType = RedisDataType.OBJECT, argName = "${table.primaryKeyColumn.propertyName}")
</#if>
    public int deleteByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
        return super.deleteByPrimaryKey(${table.primaryKeyColumn.propertyName});
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.DELETE,
        dataType = RedisDataType.OBJECT, argName = "${table.className?uncap_first}", key = "#${table.className?uncap_first}.${table.primaryKeyColumn.propertyName}",
        foreignKeys = "<#list table.foreignKeyCols as item>#${table.className?uncap_first}.${item.propertyName}<#if item_has_next>,</#if></#list>")
</#if>
    public int deleteBySelective(${table.className} ${table.className?uncap_first}) {
        return super.deleteBySelective(${table.className?uncap_first});
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.DELETE,
        dataType = RedisDataType.OBJECT, argName = "${table.className?uncap_first}", key = "#${table.className?uncap_first}.${table.primaryKeyColumn.propertyName}",
        foreignKeys = "<#list table.foreignKeyCols as item>#${table.className?uncap_first}.${item.propertyName}<#if item_has_next>,</#if></#list>")
</#if>
    public int deleteSoftBySelective(${table.className} ${table.className?uncap_first}) {
        return super.deleteSoftBySelective(${table.className?uncap_first});
    }

    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.INSERT,
        dataType = RedisDataType.OBJECT, argName = "${table.className?uncap_first}", key = "#${table.className?uncap_first}.${table.primaryKeyColumn.propertyName}",
        foreignKeys = "<#list table.foreignKeyCols as item>#${table.className?uncap_first}.${item.propertyName}<#if item_has_next>,</#if></#list>")
    </#if>
    public int insert(${table.className} ${table.className?uncap_first}) {
        return super.insert(${table.className?uncap_first});
    }

    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.INSERT,
        dataType = RedisDataType.OBJECT, argName = "${table.className?uncap_first}", key = "#${table.className?uncap_first}.${table.primaryKeyColumn.propertyName}",
        foreignKeys = "<#list table.foreignKeyCols as item>#${table.className?uncap_first}.${item.propertyName}<#if item_has_next>,</#if></#list>")
    </#if>
    public int insertSelective(${table.className} ${table.className?uncap_first}) {
        return super.insertSelective(${table.className?uncap_first});
    }

    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.UPDATE,
        dataType = RedisDataType.OBJECT, argName = "${table.className?uncap_first}", key = "#${table.className?uncap_first}.${table.primaryKeyColumn.propertyName}",
        foreignKeys = "<#list table.foreignKeyCols as item>#${table.className?uncap_first}.${item.propertyName}<#if item_has_next>,</#if></#list>")
    </#if>
    public int updateByPrimaryKey(${table.className} ${table.className?uncap_first}) {
        return super.updateByPrimaryKey(${table.className?uncap_first});
    }

    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.UPDATE,
        dataType = RedisDataType.OBJECT, argName = "${table.className?uncap_first}", key = "#${table.className?uncap_first}.${table.primaryKeyColumn.propertyName}",
        foreignKeys = "<#list table.foreignKeyCols as item>#${table.className?uncap_first}.${item.propertyName}<#if item_has_next>,</#if></#list>")
    </#if>
    public int updateByPrimaryKeySelective(${table.className} ${table.className?uncap_first}) {
        return super.updateByPrimaryKeySelective(${table.className?uncap_first});
    }

    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.DELETE,
        dataType = RedisDataType.OBJECT, argName = "${table.primaryKeyColumn.propertyName}")
    </#if>
    public int deleteSoftByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
        ${table.className} ${table.className?uncap_first} = new ${table.className}();
        ${table.className?uncap_first}.setDelete(${table.primaryKeyColumn.propertyName});
        return this.${table.className?uncap_first}${daoClassName}.updateByPrimaryKeySelective(${table.className?uncap_first});
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.SELECT,
        dataType = RedisDataType.PAGE_OBJECT, argName = "queryObject", expire = ${table.commentOption.listCacheExpire}, timeUnit = TimeUnit.${table.commentOption.listCacheUnit})
</#if>
    public PageObject<${table.className}> queryPageList(QueryObject queryObject) {
        defaultQueryObject(queryObject);
        PageHelper.startPage(queryObject.getPageNum(), queryObject.getPageSize());
        return PageObject.restPage(this.${table.className?uncap_first}${daoClassName}.queryList(queryObject));
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.SELECT,
        dataType = RedisDataType.LIST, argName = "queryObject", expire = ${table.commentOption.listCacheExpire}, timeUnit = TimeUnit.${table.commentOption.listCacheUnit})
</#if>
    public List<${table.className}> queryList(QueryObject queryObject) {
        defaultQueryObject(queryObject);
        return this.${table.className?uncap_first}${daoClassName}.queryList(queryObject);
    }

    @Override
<#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.SELECT,
        dataType = RedisDataType.TOTAL_LIST, expire = ${table.commentOption.listCacheExpire}, timeUnit = TimeUnit.${table.commentOption.listCacheUnit})
</#if>
    public List<${table.className}> queryTotalList() {
        return this.${table.className?uncap_first}${daoClassName}.queryTotalList();
    }


    @Override
    public Long count(QueryObject queryObject) {
        return this.${table.className?uncap_first}${daoClassName}.count(queryObject);
    }

<#list table.foreignKeyCols as item>
    @Override
    <#if cacheEnable == "true" && table.commentOption.cacheEnable>
    @RedisCache(schema = RedisSchema.${entityModuleName?upper_case}_${table.name?upper_case}, operate = RedisCacheOperate.SELECT,
        dataType = RedisDataType.FOREIGN_LIST, argName = "${item.propertyName}", expire = ${table.commentOption.listCacheExpire}, timeUnit = TimeUnit.${table.commentOption.listCacheUnit})
    </#if>
    public List<${table.className}> selectListBy${item.propertyName?cap_first}(${item.javaType} ${item.propertyName}) {
        return this.${table.className?uncap_first}${daoClassName}.selectListBy${item.propertyName?cap_first}(${item.propertyName});
    }

</#list>
	
}