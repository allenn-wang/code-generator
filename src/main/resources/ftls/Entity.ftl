package ${rootPackageName}.${entityPackageName};

import ${rootPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};

/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
*/
public class ${table.className} implements ${basePackageName?cap_first}${entityClassName} {
	private static final long serialVersionUID = 1L;

    <#list table.columnList as item>
    // ${item.annotation}
    private ${item.javaType} ${item.propertyName};

    </#list>

    <#list table.columnList as item>
    public ${item.javaType} get${item.propertyName?cap_first}(){
        return this.${item.propertyName};
    }

    public void set${item.propertyName?cap_first}(${item.javaType} ${item.propertyName}){
        this.${item.propertyName} = ${item.propertyName};
    }
    </#list>
    
	public void set_Id(Object id) {
		this.${table.primaryKeyColumn.propertyName} = (${table.primaryKeyColumn.javaType}) id;
	}

	public Object get_Id() {
		return this.${table.primaryKeyColumn.propertyName};
	}

	@Override
	public String toString() {
		return "${table.className} ["
		 	<#list table.columnList as item>
		 		+ "this.${item.propertyName}=" + this.${item.propertyName} + ", "
			</#list>
		+ "]";   
	}
}