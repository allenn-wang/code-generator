package ${rootPackageName}.${dtoPackageName};

import ${rootPackageName}.${entityPackageName}.${table.className};

/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
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