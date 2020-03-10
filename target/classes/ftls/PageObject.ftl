package ${rootPackageName}.${commonPackageName}.${basePackageName};

import com.github.pagehelper.PageInfo;
<#if swaggerEnable == "true">
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

import java.util.List;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:
*/
<#if swaggerEnable == "true">
@ApiModel(value = "分页对象")
</#if>
public class PageObject<T> {
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "页码")
    </#if>
    private Integer pageNum;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "页容量")
    </#if>
    private Integer pageSize;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "总页数")
    </#if>
    private Integer totalPage;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "总数")
    </#if>
    private Long total;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "分页数据")
    </#if>
    private List<T> list;

    public static <T> PageObject<T> restPage(List<T> list) {
        PageObject<T> result = new PageObject<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    public static <T> PageObject<T> copyPage(PageObject<?> pageObject, Class<T> clazz) {
        String source = JSON.toJSONString(pageObject.getList());
        List<T> target = JSON.parseArray(sourceList, clazz);
        PageObject<T> result = new PageObject<T>();
        result.setTotalPage(pageObject.getTotalPage());
        result.setPageNum(pageObject.getPageNum());
        result.setPageSize(pageObject.getPageSize());
        result.setTotal(pageObject.getTotal());
        result.setList(target);
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}