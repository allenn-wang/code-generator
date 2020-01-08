<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- ${table.annotation}
    Author：${author}
    Date: ${date}
-->
<mapper namespace="${table.className}Common">

    <resultMap id="${table.className}" type="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}">
    <#list table.columnList as item>
    <#if item.name = table.primaryKeyColumn.name>
        <id column="${item.name}" property="${item.propertyName}" jdbcType="${item.jdbcType}" javaType="${item.javaType}"/>
    <#else>
        <result column="${item.name}" property="${item.propertyName}" jdbcType="${item.jdbcType}" javaType="${item.javaType}"/>
    </#if>
    </#list>
    </resultMap>

    <sql id="Base_Column_List" >
    <#list table.columnList as item>
        ${table.name}.${item.name}<#if item_has_next>,</#if>
    </#list>
    </sql>

    <sql id="Example_Where_Clause">
        <where>
        <#if table.quickSearchCols?? && (table.quickSearchCols?size > 0)>
            <if test="keywordVal != null and keywordVal != ''">
                (
            <#list table.quickSearchCols as item>
                ${table.name}.${item.name} like ${"#"}{keywordVal}<#if item_has_next> or </#if>
            </#list>
                ) and 1 = 1
            </if>
        </#if>
            <foreach collection="conditionList" item="condition" separator="and" open="and">
				<#list table.columnList as item>
                    <if test="'${item.propertyName}' == condition.name" >
                        <choose>
                            <when test="condition.betweenValue">
                                ${table.name}.${item.name} between ${"#"}{condition.value} and ${"#"}{condition.betweenValue}
                            </when>
                            <otherwise>
                                <choose>
                                    <when test="condition.operVal == 'in'">
                                        ${table.name}.${item.name} in
                                        <foreach collection="condition.inValues" item="inItem" open="(" separator="," close=")">
                                            ${"#"}{inItem}
                                        </foreach>
                                    </when>
                                    <otherwise>
                                        ${table.name}.${item.name} ${"$"}{condition.operVal} ${"#"}{condition.value}
                                    </otherwise>
                                </choose>
                            </otherwise>
                        </choose>
                    </if>
                </#list>
            </foreach>
        </where>
    </sql>

    <sql id="Order_By_Clause">
        order by
        <foreach collection="orderSortList" item="orderSort">
        <#list table.columnList as item>
        <#if item.name != table.primaryKeyColumn.name>
            <if test="'${item.propertyName}' == orderSort.propName">
                ${table.name}.${item.name} ${"$"}{orderSort.sortType},
            </if>
        </#if>
        </#list>
        </foreach>
        ${table.name}.${table.primaryKeyColumn.name}
    </sql>
</mapper>