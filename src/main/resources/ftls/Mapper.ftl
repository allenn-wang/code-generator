<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- ${table.annotation}
    Author：${author}
    Date: ${date}
-->
<mapper namespace="${rootPackageName}.${moduleName}.${corePackageName}.${daoPackageName}.${table.className}${daoClassName}">

    <select id="queryList" parameterType="${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject" resultMap="${table.className}Common.${table.className}">
        select
        <include refid="${table.className}Common.Base_Column_List" />
        from ${table.name}
        <if test="_parameter != null">
        <where>
            <include refid="${table.className}Common.Where_Clause"/>
        </where>
        <if test= "orderBy != null and orderBy != ''">
            order by
            <include refid="${table.className}Common.Order_By_Clause"/>
        </if>
        </if>
    </select>

    <select id="queryTotalList" resultMap="${table.className}Common.${table.className}">
        select
        <include refid="${table.className}Common.Base_Column_List" />
        from ${table.name}
        where is_delete = 0
    </select>

    <select id="count" parameterType="${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject" resultType="java.lang.Long">
        select count(*) total from ${table.name}
        <if test="_parameter != null">
        <where>
            <include refid="${table.className}Common.Where_Clause"/>
        </where>
        </if>
    </select>

    <select id="selectByPrimaryKey" parameterType="java.lang.${table.primaryKeyColumn.javaType}" resultMap="${table.className}Common.${table.className}">
        select
        <include refid="${table.className}Common.Base_Column_List" />
        from ${table.name}
        where ${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
    </select>

    <#list table.foreignKeyCols as item>
    <select id="selectListBy${item.propertyName?cap_first}" parameterType="java.lang.${item.javaType}" resultMap="${table.className}Common.${table.className}">
        select
        <include refid="${table.className}Common.Base_Column_List" />
        from ${table.name}
        where ${table.name}.${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}}
            and ${table.name}.is_delete = 0
    </select>

    </#list>
	<delete id="deleteByPrimaryKey" parameterType="java.lang.${table.primaryKeyColumn.javaType}" >
        delete from ${table.name}
        where ${table.primaryKeyColumn.name} = ${"#{"}${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
	</delete>

	<delete id="deleteBySelective" parameterType="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}" >
        delete from ${table.name}
        where
        <trim suffixOverrides="and">
    <#list table.columnList as item>
            <if test="${item.propertyName} != null" >
                ${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}} and
            </if>
	</#list>
        </trim>
	</delete>

	<update id="deleteSoftBySelective" parameterType="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}" >
        update  ${table.name}
        set is_delete = 1
        where
        <trim suffixOverrides="and">
    <#list table.columnList as item>
            <if test="${item.propertyName} != null" >
                ${item.name} = ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}} and
            </if>
	</#list>
        </trim>
	</update>

    <insert id="insert" parameterType="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}">
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
    
    <insert id="insertSelective" parameterType="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}">
        insert into ${table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list table.columnList as item>
                <if test="${item.propertyName} != null">
                    ${item.name},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list table.columnList as item>
            <if test="${item.propertyName} != null">
                ${"#{"}${item.propertyName},jdbcType=${item.jdbcType}},
			</if>
		</#list>
    	</trim>
    </insert>
    
	<update id="updateByPrimaryKey" parameterType="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}" >
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
  
	<update id="updateByPrimaryKeySelective" parameterType="${rootPackageName}.${commonPackageName}.${modulePackageName}.${entityPackageName}.${table.className}" >
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

    <update id="batchUpdateSelective"  parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" open="" close="" separator=";">
            update ${table.name}
            <set>
        <#list table.columnList as col>
		    <#if col.name != table.primaryKeyColumn.name>
			    <if test="item.${col.propertyName} != null" >
                    ${col.name} = ${"#{"}item.${col.propertyName},jdbcType=${col.jdbcType}},
			    </if>
		    </#if>
        </#list>
            </set>
            where ${table.primaryKeyColumn.name} = ${"#{"}item.${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
        </foreach>
    </update>

    <update id="batchUpdate"  parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" open="" close="" separator=";">
            update ${table.name}
            <set>
        <#list table.columnList as col>
		    <#if col.name != table.primaryKeyColumn.name>
                ${col.name} = ${"#{"}item.${col.propertyName},jdbcType=${col.jdbcType}},
		    </#if>
        </#list>
            </set>
            where ${table.primaryKeyColumn.name} = ${"#{"}item.${table.primaryKeyColumn.propertyName},jdbcType=${table.primaryKeyColumn.jdbcType}}
        </foreach>
    </update>
</mapper>