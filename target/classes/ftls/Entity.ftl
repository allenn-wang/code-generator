package ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${entityPackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}${entityClassName};

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  ${table.annotation}
*/
@Getter
@Setter
@ToString
public class ${table.className} implements ${basePackageName?cap_first}${entityClassName} {
	private static final long serialVersionUID = 1L;
    <#list table.columnList as item>

    // ${item.annotation}
	<#if item.javaType=="Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
	private ${item.javaType} ${item.propertyName};
    </#list>

	public void set_Id(Object id) {
		this.${table.primaryKeyColumn.propertyName} = (${table.primaryKeyColumn.javaType}) id;
	}

	public Object get_Id() {
		return this.${table.primaryKeyColumn.propertyName};
	}
}