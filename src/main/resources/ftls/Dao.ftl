package ${basePackageName}.${daoPackageName};

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ${basePackageName}.${daoPackageName}.base.Base${daoClassName};
import ${basePackageName}.${domainPackageName}.${table.className}${dtoClassName};
import ${basePackageName}.${entityPackageName}.${table.className};

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：${table.annotation}
*/
@Mapper
public interface ${table.className}${daoClassName} extends Base${daoClassName}<${table.className}> {

	${table.className}${dtoClassName} select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName});
}