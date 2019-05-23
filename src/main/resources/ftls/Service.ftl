package ${basePackageName}.${servicePackageName};

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ${basePackageName}.${dtoPackageName}.${table.className}${dtoClassName};
import ${basePackageName}.${servicePackageName}.base.Base${serviceClassName};

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：${table.annotation}
*/
public interface ${table.className}${serviceClassName} extends Base${serviceClassName}<${table.className}> {

    ${table.className}${dtoClassName} select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName});

    ${table.className}${dtoClassName} update${dtoClassName}ByPrimaryKeySelective(${table.className} ${table.className?uncap_first});
}