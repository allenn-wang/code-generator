package ${rootPackageName}.${daoPackageName};

import ${rootPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};
import ${rootPackageName}.${dtoPackageName}.${table.className}${dtoClassName};
import ${rootPackageName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${entityPackageName}.${table.className}Example;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
*/
@Mapper
public interface ${table.className}${daoClassName} extends ${basePackageName?cap_first}${daoClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> {

	${table.className}${dtoClassName} select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName});

    List<${table.className}${dtoClassName}> queryList${dtoClassName}(${table.className}Example ${table.className?uncap_first}Example);

    List<${table.className}> queryList(${table.className}Example ${table.className?uncap_first}Example);

    Long count(${table.className}Example ${table.className?uncap_first}Example);
}