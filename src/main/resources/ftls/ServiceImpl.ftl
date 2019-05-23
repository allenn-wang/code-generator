package ${basePackageName}.${serviceImplPackageName};

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ${basePackageName}.${daoPackageName}.base.Base${daoClassName};
import ${basePackageName}.${servicePackageName}.base.Base${serviceClassName}${serviceImplClassName};
import ${basePackageName}.${daoPackageName}.${table.className}${daoClassName};
import ${basePackageName}.${dtoPackageName}.${table.className}${dtoClassName};
import ${basePackageName}.${entityPackageName}.${table.className};
import ${basePackageName}.${servicePackageName}.${table.className}${serviceClassName};

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：${table.annotation}
*/
@Service
public class ${table.className}${serviceClassName}${serviceImplClassName} extends Base${serviceClassName}${serviceImplClassName}<${table.className}> implements ${table.className}${serviceClassName} {
	private static final Logger logger = LoggerFactory.getLogger(${table.className}${serviceClassName}${serviceImplClassName}.class);

	@Autowired
	private ${table.className}${daoClassName} ${table.className?uncap_first}${daoClassName};

	@Override
	public Base${daoClassName}<${table.className}> getBase${daoClassName}() {
		return this.${table.className?uncap_first}${daoClassName};
	}
	
	@Override
	public ${table.className}${dtoClassName} select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
		return this.${table.className?uncap_first}${daoClassName}.select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.propertyName});
	}
	
	@Override
	public ${table.className}${dtoClassName} update${dtoClassName}ByPrimaryKeySelective(${table.className} ${table.className?uncap_first}) {
		this.${table.className?uncap_first}${daoClassName}.updateByPrimaryKeySelective(${table.className?uncap_first});
		return this.${table.className}${daoClassName}.select${dtoClassName}ByPrimaryKey(${table.className?uncap_first}.get_Id());
	}
	
}