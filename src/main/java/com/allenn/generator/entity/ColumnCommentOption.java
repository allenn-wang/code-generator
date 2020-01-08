package com.allenn.generator.entity;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 业务注释
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class ColumnCommentOption {
    /*
     * 业务注释格式: <业务名>!<数据业务类型>!<业务说明json对象>(!<枚举值>)
     *   eg. 名称!string!{"required":"true","length":"255"}
     *       学校性质!enum!{"required":"true"}!{"public":"公办","private":"民办"}
     *
     *  <业务数据类型>: Constant.FieldServiceType
     *      <业务数据类型>为enum时必需<枚举值>
     *      <枚举值>: json字符串, 枚举业务值-枚举业务值说明
     **/

    // 业务名 生成属性注释
    private String label;

    // 业务数据类型
    private String dataType;

    // 是否业务必需, 生成swagger非空注解以及validation校验
    private boolean required = false;

    // swagger api注解是否隐藏
    private boolean apiHidden = false;

    // 是否关键字like搜索列
    private boolean quickSearch = false;

    // 是否高级搜索列
    private boolean highSearch = false;

    // 是否关联查询展示
    private boolean briefShow = false;

    // 业务类型为string时, 正则校验类型
    // 可用值: telephone-固定电话号码  phone-手机号码
    //        email-邮箱  idcard-身份证号  authcode-验证码
    private String formatter;

    // 业务类型为join时, 外键关联的表名
    private String joinTable;

    // 业务类型为join时, 外键关联的表java属性名
    private String joinPropertyName;

    // 生成swagger注解以及validation校验最大值
    private String maxValue;

    // 生成swagger注解以及validation校验最小值
    private String minValue;

    // 生成swagger注解以及validation校验最大长度
    private String maxLength;

    // 生成swagger注解枚举值
    private Map<String, String> allowValues = new HashMap<>();

    // 关联子表名列表
    private List<String> childTables = new LinkedList<>();

    //补充集合
    private Map<String,String> supplementMap = new HashMap<>();



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isApiHidden() {
        return apiHidden;
    }

    public void setApiHidden(boolean apiHidden) {
        this.apiHidden = apiHidden;
    }

    public boolean isQuickSearch() {
        return quickSearch;
    }

    public void setQuickSearch(boolean quickSearch) {
        this.quickSearch = quickSearch;
    }

    public boolean isHighSearch() {
        return highSearch;
    }

    public void setHighSearch(boolean highSearch) {
        this.highSearch = highSearch;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable;
    }

    public String getJoinPropertyName() {
        return joinPropertyName;
    }

    public void setJoinPropertyName(String joinPropertyName) {
        this.joinPropertyName = joinPropertyName;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public Map<String, String> getAllowValues() {
        return allowValues;
    }

    public void setAllowValues(Map<String, String> allowValues) {
        this.allowValues = allowValues;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> getChildTables() {
        return childTables;
    }

    public void setChildTables(List<String> childTables) {
        this.childTables = childTables;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public boolean isBriefShow() {
        return briefShow;
    }

    public void setBriefShow(boolean briefShow) {
        this.briefShow = briefShow;
    }

    public Map<String, String> getSupplementMap() {
        return supplementMap;
    }

    public void setSupplementMap(Map<String, String> supplementMap) {
        this.supplementMap = supplementMap;
    }
}
