package ${basePackageName}.${entityPackageName};

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ${basePackageName}.${entityPackageName}.base.Base${entityClassName};

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：${table.annotation}
*/
public class ${table.className} implements Base${entityClassName} {
	private static final long serialVersionUID = 1L;

    <#list table.columnList as item>
    // ${item.annotation}
	// isNullAble: ${item.nullAble}   length: ${item.length}   scale：${item.scale}
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
    
    @Override
	public void set_Id(Object id) {
		this.${table.primaryKeyColumn.propertyName} = (${table.primaryKeyColumn.javaType}) id;
	}

	@Override
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