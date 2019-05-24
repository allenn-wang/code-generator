<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- ${table.annotation} Author：${author} Date: ${date} -->
<mapper namespace="${rootPackageName}.${daoPackageName}.${table.className}${daoClassName}" >
    <resultMap id="${table.className}" type="${rootPackageName}.${entityPackageName}.${table.className}">
        <#list table.columnList as item>
        <#if item.name = table.primaryKeyColumn.name>
        	<id column="${item.name}" property="${item.propertyName}" jdbcType="${item.jdbcType}" javaType="${item.javaType}"/>
        <#else>
        	<result column="${item.name}" property="${item.propertyName}" jdbcType="${item.jdbcType}" javaType="${item.javaType}"/>
        </#if>
        </#list>
    </resultMap>
    
    <resultMap id="${table.className}${dtoClassName}" type="${rootPackageName}.${dtoPackageName}.${table.className}${dtoClassName}">
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
		${item.name}<#if item_has_next>,</#if>
		</#list>  
	</sql>
	
	<sql id="Base_Column_List_Dto" >
		<#list table.columnList as item>
		${table.name}.${item.name}<#if item_has_next>,</#if>
		</#list>  
	</sql>
	
    <select id="selectByPrimaryKey" parameterType="java.lang.${table.primaryKeyColumn.javaType}" resultMap="${table.className}">
		select
			<include refid="Base_Column_List" />
	<![CDATA[
		from ${table.name}
		where ${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
	 ]]>
    </select>
    
    <select id="selectDtoByPrimaryKey" parameterType="java.lang.${table.primaryKeyColumn.javaType}" resultMap="${table.className}${dtoClassName}">
		select
			<include refid="Base_Column_List_Dto" />
	<![CDATA[
		from ${table.name}
		where ${table.name}.${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
	 ]]>
    </select>
    
	<delete id="deleteByPrimaryKey" parameterType="java.lang.${table.primaryKeyColumn.javaType}" >
		delete from ${table.name}
		where ${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
	</delete>
  
    <insert id="insert" parameterType="${rootPackageName}.${entityPackageName}.${table.className}">
	<![CDATA[
		insert into ${table.name}(
		<#list table.columnList as item>
			${item.name}<#if item_has_next>,</#if>
		</#list>
		) values (
		<#list table.columnList as item>
			${"#{"}${item.propertyName},jdbcType=${item.jdbcType}}<#if item_has_next>,</#if>
		</#list>
		)
	]]>
    </insert>
    
    <insert id="insertSelective" parameterType="${rootPackageName}.${entityPackageName}.${table.className}">
    	insert into ${table.name}
    	<trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.columnList as item>
			<if test="${item.propertyName} != null" >
				${item.name},
			</if>
		</#list>
    	</trim>
    	<trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list table.columnList as item>
			 <if test="${item.propertyName} != null" >
			   ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}},
			  </if>
		</#list>
    	</trim>
    </insert>
    
	<update id="updateByPrimaryKey" parameterType="${rootPackageName}.${entityPackageName}.${table.className}" >
		update  ${table.name}
	    <set>
	<#list table.columnList as item>
		<#if item.name != table.primaryKeyColumn.name>
			${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}},
		</#if>
	</#list>
		</set>
		where ${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
	</update>
  
	<update id="updateByPrimaryKeySelective" parameterType="${rootPackageName}.${entityPackageName}.${table.className}" >
  		update  ${table.name}
  		<set>
	<#list table.columnList as item>
		<#if item.name != table.primaryKeyColumn.name>
			<if test="${item.propertyName} != null" >
				${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}},
			</if>
		</#if>
	</#list>
  		</set>
		where ${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
	</update>

	<sql id="pageWhere">
		<where>
  	    </where>
  	</sql>

    <sql id="joinSql"></sql>
    
    <insert id="batchInsert" parameterType="java.util.List">
		insert into ${table.name}(
		<#list table.columnList as item>
			${item.name}<#if item_has_next>,</#if>
		</#list>
		) values
		<foreach collection="list" item="item" index="index" separator=",">
		 (<#list table.columnList as item>
			${"#{"}item.${item.propertyName},jdbcType=${item.jdbcType}}<#if item_has_next>,</#if>
		</#list>)
		</foreach>
    </insert>

</mapper>