package ${rootPackageName}.${commonPackageName}.${basePackageName};

<#if swaggerEnable == "true">
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 查询条件
 * @Author: ${author}
 * @Date: ${date}
 */
@Getter
@Setter
<#if swaggerEnable == "true">
@ApiModel(value = "查询条件")
</#if>
public class QueryCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "过滤属性名")
    </#if>
    private String name;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "过滤操作 =|>|>=|<|<=|!=|<>|in|like")
    </#if>
    private String oper;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "过滤属性值")
    </#if>
    private Object value;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "过滤属性between值")
    </#if>
    private Object betweenValue;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "过滤属性in值")
    </#if>
    private List<Object> inValues;

    <#if swaggerEnable == "true">
    @ApiModelProperty(hidden = true)
    </#if>
    private String operVal;

    public String getOperVal() {
        String[] vaildVals = new String[]{"=", ">", ">=", "<", "<=", "!=", "<>", "in", "like"};
        if (Arrays.asList(vaildVals).contains(this.oper)) {
            return this.oper;
        } else {
            return "=";
        }

    }

    public QueryCondition() {

    }

    public QueryCondition(String name, Object value) {
        this(name, "=", value, null, null);
    }

    public QueryCondition(String name, String oper, Object value) {
        this(name, oper, value, null, null);
    }

    public QueryCondition(String name, String oper, Object value, Object betweenValue, List inValues) {
        this.name = name;
        this.oper = oper;
        this.value = value;
        this.betweenValue = betweenValue;
        this.inValues = inValues;
    }

    public QueryCondition(String name, List inValues) {
        this(name, "in", null, null, inValues);
    }
}
