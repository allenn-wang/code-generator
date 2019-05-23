package ${basePackageName}.${dtoPackageName};

import ${basePackageName}.${entityPackageName}.${table.className};

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：${table.annotation}
*/
public class ${table.className}${dtoClassName} extends ${table.className} {

	@Override
	public String toString() {
		return "${table.className}${dtoClassName} ["
		 	<#list table.columnList as item>
		 		+ "this.${item.propertyName}=" + this.get${item.propertyName?cap_first}() + ", "
			</#list>
		+ "]";   
	}

}