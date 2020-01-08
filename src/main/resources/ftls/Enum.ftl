package ${rootPackageName}.${commonPackageName}.${enumPackageName}.${moduleName};

import ${rootPackageName}.${commonPackageName}.${enumPackageName}.IEnum;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  ${enumAnnotation}
*/
public enum ${enumClassName} implements IEnum {

<#list enumValues?keys as key>
    <#if enumValueType == "String">
    ${key}("${key}", "${enumValues[key]}"),
    <#else>
    _${key}(${key}, "${enumValues[key]}"),
    </#if>
</#list>
    ;

    private ${enumValueType} value;
    private String desc;

    ${enumClassName}(${enumValueType} value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getIntegerValue() {
<#if enumValueType == "String">
        return null;
<#else>
        return this.value;
</#if>
    }

    @Override
    public String getStringValue() {
<#if enumValueType == "String">
        return this.value;
<#else>
        return null;
</#if>
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}