package ${rootPackageName}.${commonPackageName}.${basePackageName};

<#if swaggerEnable == "true">
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 通用查询对象
 * @Author: ${author}
 * @Date: ${date}
 */
@Getter
@Setter
<#if swaggerEnable == "true">
@ApiModel(value = "查询对象")
</#if>
@JsonIgnoreProperties({"orderSortList"})
public class QueryObject implements Serializable {
    private static final long serialVersionUID = 1L;

    // 页码
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "页码", example = "1")
    </#if>
    private Integer pageNum = 1;

    // 页容量
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "页容量", example = "20")
    </#if>
    private Integer pageSize = 20;

    // 排序字段
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "排序 eg: updateTime( asc),addTime( desc), ()为选填内容,缺省数据库默认desc,单空格分隔 ")
    </#if>
    private String orderBy;

    // 高级查询
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "关键词查询")
    </#if>
    private String keyword;

    <#if swaggerEnable == "true">
    @ApiModelProperty(hidden = true)
    </#if>
    private String keywordVal;

    <#if swaggerEnable == "true">
    @ApiModelProperty(hidden = true)
    </#if>
    private List<Sort> orderSortList = new LinkedList<>();

    // 查询条件 and 连接
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "查询条件")
    </#if>
    private List<QueryCondition> conditionList = Collections.synchronizedList(new LinkedList<QueryCondition>());

    public String getKeywordVal() {
        if (StringUtils.isBlank(this.keyword)) {
            return this.keyword;
        }
        return "%" + this.keyword + "%";
    }

    public QueryObject addCondition(QueryCondition queryCondition) {
        this.conditionList.add(queryCondition);
        return this;
    }

    public QueryObject addNotDeleteCondition() {
        this.conditionList.add(new QueryCondition("isDelete", 0));
        return this;
    }

    public static QueryObject getQueryObject(String key, String value) {
        QueryObject queryObject = new QueryObject();
        queryObject.addNotDeleteCondition().addCondition(new QueryCondition(key, value));
        return queryObject;
    }


    public List<Sort> getOrderSortList() {
        List<Sort> orderSortList = new LinkedList<>();
        if (StringUtils.isNotBlank(this.orderBy)) {
            String[] orderSorts = this.orderBy.split(",");
            for (String orderSort : orderSorts) {
                if (StringUtils.isNotBlank(orderSort)) {
                    if (orderSort.contains(" ")) {
                        String[] orders = orderSort.split(" ");
                        Sort sort = new Sort();
                        sort.setPropName(orders[0]);
                        sort.setSortType(orders[1]);
                        orderSortList.add(sort);
                    } else {
                        Sort sort = new Sort();
                        sort.setPropName(orderSort);
                        sort.setSortType("desc");
                        orderSortList.add(sort);
                    }
                }
            }
        }
        return orderSortList;
    }

    public class Sort {
        private String propName;
        private String sortType;

        public Sort() {

        }

        public Sort(String propName, String sortType) {
            this.propName = propName;
            this.sortType = sortType;
        }

        public String getPropName() {
            return propName;
        }

        public void setPropName(String propName) {
            this.propName = propName;
        }

        public String getSortType() {
            return sortType;
        }

        public void setSortType(String sortType) {
            this.sortType = sortType;
        }
    }
}
