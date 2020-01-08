package ${rootPackageName}.${moduleName}.${corePackageName}.${controllerPackageName};

import ${rootPackageName}.${commonPackageName}.${modulePackageName}.${moduleName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${moduleName}.${corePackageName}.${servicePackageName}.${table.className}${serviceClassName};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.${controllerPackageName}.${basePackageName?cap_first}${controllerClassName};
import ${rootPackageName}.${commonPackageName}.${basePackageName}.QueryObject;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.PageObject;
import ${rootPackageName}.${commonPackageName}.${basePackageName}.ResultObject;

import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if swaggerEnable == "true">
import io.swagger.annotations.*;
</#if>

import java.util.List;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:  ${table.annotation}
*
*  @Api(description = "${table.annotation}接口")
*  @ApiVersion
*  @Slf4j
*  @RestController
*  @RequestMapping("/{version}/${table.className?uncap_first}")
*/
public class ${table.className}${controllerClassName} extends ${basePackageName?cap_first}${controllerClassName} {

	@Autowired
	protected ${table.className}${serviceClassName} ${table.className?uncap_first}${serviceClassName};

<#if swaggerEnable == "true">
    @ApiOperation(value = "添加${table.annotation}", notes = "添加${table.annotation}")
</#if>
    @PostMapping
    public ResultObject add(@Validated @RequestBody ${table.className} ${table.className?uncap_first}) {
        int result = ${table.className?uncap_first}${serviceClassName}.insertSelective(${table.className?uncap_first});
        if (result > 0) {
            return ResultObject.success(result);
        } else {
            return ResultObject.failed();
        }
    }

<#if swaggerEnable == "true">
    @ApiOperation(value = "修改${table.annotation}", notes = "根据${table.primaryKeyColumn.propertyName}修改${table.annotation}")
</#if>
    @PutMapping
    public ResultObject update(@Validated @RequestBody ${table.className} ${table.className?uncap_first}){
        int result = ${table.className?uncap_first}${serviceClassName}.updateByPrimaryKeySelective(${table.className?uncap_first});
        if (result > 0) {
            return ResultObject.success(result);
        } else {
            return ResultObject.failed();
        }
    }

<#if swaggerEnable == "true">
    @ApiOperation(value = "删除${table.annotation}", notes = "根据${table.primaryKeyColumn.propertyName}删除${table.annotation}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${table.primaryKeyColumn.propertyName}", value = "${table.annotation}ID", required = true, paramType = "path", dataType = "String")
    })
</#if>
    @DeleteMapping(value = "/{${table.primaryKeyColumn.propertyName}}")
    public ResultObject delete(@PathVariable("${table.primaryKeyColumn.propertyName}") ${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
        int result = ${table.className?uncap_first}${serviceClassName}.deleteSoftByPrimaryKey(${table.primaryKeyColumn.propertyName});
        if (result > 0) {
            return ResultObject.success();
        } else {
            return ResultObject.failed();
        }
    }

<#if swaggerEnable == "true">
    @ApiOperation(value = "${table.annotation}分页查询", notes = "${table.annotation}分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryObject", value = "分页查询参数", required = false, paramType = "body", dataType = "QueryObject")
    })
</#if>
    @PostMapping(value = "/page")
    public ResultObject<PageObject<${table.className}>> queryPage(@RequestBody(required = false) QueryObject queryObject) {
        return ResultObject.success(${table.className?uncap_first}${serviceClassName}.queryPageList(queryObject));
    }

<#if swaggerEnable == "true">
    @ApiOperation(value = "${table.annotation}查询", notes = "${table.annotation}查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryObject", value = "查询参数", required = false, paramType = "body", dataType = "QueryObject")
    })
</#if>
    @PostMapping(value = "/list")
    public ResultObject<List<${table.className}>> query(@RequestBody(required = false) QueryObject queryObject) {
    return ResultObject.success(${table.className?uncap_first}${serviceClassName}.queryList(queryObject));
    }

<#if swaggerEnable == "true">
    @ApiOperation(value = "查询${table.annotation}", notes = "根据${table.primaryKeyColumn.propertyName}查询${table.annotation}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${table.primaryKeyColumn.propertyName}", value = "${table.annotation}ID", required = true, paramType = "path", dataType = "String")
    })
</#if>
    @GetMapping(value = "/{${table.primaryKeyColumn.propertyName}}")
    public ResultObject<${table.className}> get(@PathVariable("${table.primaryKeyColumn.propertyName}") ${table.primaryKeyColumn.javaType} ${table.primaryKeyColumn.propertyName}) {
        ${table.className} ${table.className?uncap_first} = ${table.className?uncap_first}${serviceClassName}.selectByPrimaryKey(${table.primaryKeyColumn.propertyName});
        return ResultObject.success(${table.className?uncap_first});
    }
}