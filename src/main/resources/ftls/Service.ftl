package ${rootPackageName}.${moduleName}.${corePackageName}.${servicePackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${servicePackageName}.${basePackageName?cap_first}${serviceClassName};
import ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.PageObject;

import java.util.List;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  ${table.annotation}
*/
public interface ${table.className}${serviceClassName} extends ${basePackageName?cap_first}${serviceClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> {

    int deleteSoftByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName});

    PageObject<${table.className}> queryPageList(QueryObject queryObject);

    List<${table.className}> queryList(QueryObject queryObject);

    List<${table.className}> queryTotalList();

    Long count(QueryObject queryObject);

<#list table.foreignKeyCols as item>
    List<${table.className}> queryListBy${item.propertyName?cap_first}(${item.javaType} ${item.propertyName});

</#list>
}