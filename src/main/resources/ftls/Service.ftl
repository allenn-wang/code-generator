package ${rootPackageName}.${servicePackageName};

import ${rootPackageName}.${dtoPackageName}.${table.className}${dtoClassName};
import ${rootPackageName}.${basePackageName}.${servicePackageName}.${basePackageName?cap_first}${serviceClassName};
import ${rootPackageName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${entityPackageName}.${table.className}Example;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
*/
public interface ${table.className}${serviceClassName} extends ${basePackageName?cap_first}${serviceClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> {

    ${table.className}${dtoClassName} select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName});

    ${table.className}${dtoClassName} update${dtoClassName}ByPrimaryKeySelective(${table.className} ${table.className?uncap_first});

    List<${table.className}${dtoClassName}> queryList${dtoClassName}(${table.className}Example ${table.className?uncap_first}Example, Integer pageNum, Integer pageSize);

    List<${table.className}> queryList(${table.className}Example ${table.className?uncap_first}Example, Integer pageNum, Integer pageSize);

    Long count(${table.className}Example ${table.className?uncap_first}Example);
}