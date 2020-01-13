package ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${boPackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.${entityPackageName}.${basePackageName?cap_first}Pojo;
import ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${entityPackageName}.${table.className};

import org.springframework.beans.BeanUtils;
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
<#if swaggerEnable == "true">
@ApiModel(value = "${table.annotation}")
</#if>
public class ${table.className}${boClassName} implements ${basePackageName?cap_first}Pojo<${table.className}> {
    <#list table.columnList as item>
	<#if !systemColumns?seq_contains(item.name)>

    // ${item.annotation}
	<#if swaggerEnable == "true">
	@ApiModelProperty(value = "${item.commentOption.label}<#list item.commentOption.allowValues?keys as key>  ${key}:${item.commentOption.allowValues[key]}</#list>")
	</#if>
	<#if item.javaType=="Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
	private ${item.javaType} ${item.propertyName};
	</#if>
    </#list>

	public ${table.className} convert() {
		${table.className} entity = new ${table.className}();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}

	public void build(${table.className} entity) {
		BeanUtils.copyProperties(entity, this);
	}
}