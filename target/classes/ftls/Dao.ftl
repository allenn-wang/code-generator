package ${rootPackageName}.${moduleName}.${corePackageName}.${daoPackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};
import ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  ${table.annotation}
*/
@Mapper
public interface ${table.className}${daoClassName} extends ${basePackageName?cap_first}${daoClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> {

    List<${table.className}> queryList(QueryObject queryObject);

    List<${table.className}> queryTotalList();

    <#list table.foreignKeyCols as item>
    List<${table.className}> selectListBy${item.propertyName?cap_first}(${item.javaType} ${item.propertyName});

    </#list>
    Long count(QueryObject queryObject);
}