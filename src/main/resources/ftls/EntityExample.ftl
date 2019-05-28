package ${rootPackageName}.${entityPackageName};

import ${rootPackageName}.${basePackageName}.${entityPackageName}.Criterion;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
*/
public class ${table.className}Example implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Criterion> criteria = new LinkedList<>();
    private String orderByClause;

    <#list table.columnList as item>
    public ${table.className}Example and${item.propertyName?cap_first}IsNull(){
        criteria.add(new Criterion("${table.name}.${item.name} is null"));
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}IsNotNull(){
        criteria.add(new Criterion("${table.name}.${item.name} is not null"));
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}EqualTo(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} =", ${item.propertyName}));
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}NotEqualTo(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} <>", ${item.propertyName}));
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}In(List<${item.javaType}> ${item.propertyName}List) {
        criteria.add(new Criterion("${table.name}.${item.name} in", ${item.propertyName}List));
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}NotIn(List<${item.javaType}> ${item.propertyName}List) {
        criteria.add(new Criterion("${table.name}.${item.name} not in", ${item.propertyName}List));
        return this;
    }

    <#if (item.javaType = "Long" || item.javaType = "Integer")>
    public ${table.className}Example and${item.propertyName?cap_first}GreaterThan(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} >", ${item.propertyName});
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}GreaterThanOrEqualTo(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} >=", ${item.propertyName});
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}LessThan(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} <", ${item.propertyName});
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}LessThanOrEqualTo(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} <=", ${item.propertyName});
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}Between(${item.javaType} value1, ${item.javaType} value2) {
        addCriterion("${table.name}.${item.name} between", value1, value2);
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}NotBetween(${item.javaType} value1, ${item.javaType} value2) {
        addCriterion("${table.name}.${item.name} not between", value1, value2);
        return this;
    }
    <#elseif  item.javaType = "String">
    public ${table.className}Example and${item.propertyName?cap_first}Like(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} like", ${item.propertyName}));
        return this;
    }

    public ${table.className}Example and${item.propertyName?cap_first}NotLike(${item.javaType} ${item.propertyName}) {
        criteria.add(new Criterion("${table.name}.${item.name} not like", ${item.propertyName}));
        return this;
    }
    </#if>
    </#list>

    public boolean isValid() {
        return criteria.size() > 0;
    }

    public void clear() {
        criteria.clear();
        orderByClause = null;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }
}