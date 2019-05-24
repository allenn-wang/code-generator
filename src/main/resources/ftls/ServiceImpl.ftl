package ${rootPackageName}.${serviceImplPackageName};

import ${rootPackageName}.${basePackageName}.${daoPackageName}.${basePackageName?cap_first}${daoClassName};
import ${rootPackageName}.${basePackageName}.${serviceImplPackageName}.${basePackageName?cap_first}${serviceClassName}${serviceImplClassName};
import ${rootPackageName}.${daoPackageName}.${table.className}${daoClassName};
import ${rootPackageName}.${dtoPackageName}.${table.className}${dtoClassName};
import ${rootPackageName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${servicePackageName}.${table.className}${serviceClassName};

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
*/
@Service
public class ${table.className}${serviceClassName}${serviceImplClassName} extends ${basePackageName?cap_first}${serviceClassName}${serviceImplClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> implements ${table.className}${serviceClassName} {
	private static final Logger logger = Logger.getLogger(${table.className}${serviceClassName}${serviceImplClassName}.class);

	@Autowired
	private ${table.className}${daoClassName} ${table.className?uncap_first}${daoClassName};

	@Override
	public Base${daoClassName}<${table.className}, ${table.primaryKeyColumn.javaType}> get${basePackageName?cap_first}${daoClassName}() {
		return this.${table.className?uncap_first}${daoClassName};
	}
	
	public ${table.className}${dtoClassName} select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
		return this.${table.className?uncap_first}${daoClassName}.select${dtoClassName}ByPrimaryKey(${table.primaryKeyColumn.propertyName});
	}
	
	public ${table.className}${dtoClassName} update${dtoClassName}ByPrimaryKeySelective(${table.className} ${table.className?uncap_first}) {
		this.${table.className?uncap_first}${daoClassName}.updateByPrimaryKeySelective(${table.className?uncap_first});
		return this.${table.className?uncap_first}${daoClassName}.select${dtoClassName}ByPrimaryKey((${table.primaryKeyColumn.javaType}) ${table.className?uncap_first}.get_Id());
	}
	
}