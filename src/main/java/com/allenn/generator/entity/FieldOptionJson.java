package com.allenn.generator.entity;

/**
 * @Description: 业务说明json对象
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class FieldOptionJson {
    // 表级注释 是否缓存 默认关闭
    private String cacheEnable;

    // 表级注释 单对象缓存失效时间
    // 格式：时间 + 单位(s-秒 m-分钟 h-小时 d-天)  ^\\d{1,10}(s|S|m|M|h|H|D|d)?$
    private String singleCacheExpire;

    // 表级注释 列表对象缓存失效时间
    // 格式：时间 + 单位(s-秒 m-分钟 h-小时 d-天) ^\\d{1,10}(s|S|m|M|h|H|D|d)?$
    private String listCacheExpire;

    // 是否业务必需, 生成swagger非空注解以及validation校验
    private String required;

    // 业务类型为string时, 正则校验类型
    // 可用值: telephone-固定电话号码  phone-手机号码
    //        email-邮箱  idcard-身份证号  authcode-验证码
    private String formatter;

    // swagger api注解是否隐藏
    private String isApiHidden = "false";

    // 业务类型为int时, 最大值
    private Integer maxValue;

    // 业务类型为int时, 最小值
    private Integer minValue;

    // 业务类型为string时, 最大长度
    private String maxLength;

    // 业务类型为join时, 外键关联的表名
    private String table;

    // 业务类型为key时, 子表表名
    private String child;

    // 是否关键字like搜索列
    private String quickSearch;

    // 是否高级搜索列
    private String highSearch;

    // 关联查询是否展示
    private String briefShow;

    // 字段业务排序号
    private Integer sort;

    // 列表是否展示
    private String listShow;

    // 新增是否展示
    private String addShow;

    // 编辑是否展示
    private String editShow;

    // 详情是否展示
    private String detailShow;

    // 是否支持排序
    private String sortable;

    // 宽度
    private Integer width;

    // 宽度
    private Integer height;

    // 编辑宽度
    private Integer editWidth;

    //补充集合
    private String supplement;

    //补充集合
    private String supplementMsg;

    public String getCacheEnable() {
        return cacheEnable;
    }

    public void setCacheEnable(String cacheEnable) {
        this.cacheEnable = cacheEnable;
    }

    public String getSingleCacheExpire() {
        return singleCacheExpire;
    }

    public void setSingleCacheExpire(String singleCacheExpire) {
        this.singleCacheExpire = singleCacheExpire;
    }

    public String getListCacheExpire() {
        return listCacheExpire;
    }

    public void setListCacheExpire(String listCacheExpire) {
        this.listCacheExpire = listCacheExpire;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getIsApiHidden() {
        return isApiHidden;
    }

    public void setIsApiHidden(String isApiHidden) {
        this.isApiHidden = isApiHidden;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getQuickSearch() {
        return quickSearch;
    }

    public void setQuickSearch(String quickSearch) {
        this.quickSearch = quickSearch;
    }

    public String getHighSearch() {
        return highSearch;
    }

    public void setHighSearch(String highSearch) {
        this.highSearch = highSearch;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getListShow() {
        return listShow;
    }

    public void setListShow(String listShow) {
        this.listShow = listShow;
    }

    public String getAddShow() {
        return addShow;
    }

    public void setAddShow(String addShow) {
        this.addShow = addShow;
    }

    public String getEditShow() {
        return editShow;
    }

    public void setEditShow(String editShow) {
        this.editShow = editShow;
    }

    public String getDetailShow() {
        return detailShow;
    }

    public void setDetailShow(String detailShow) {
        this.detailShow = detailShow;
    }

    public String getSortable() {
        return sortable;
    }

    public void setSortable(String sortable) {
        this.sortable = sortable;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getEditWidth() {
        return editWidth;
    }

    public void setEditWidth(Integer editWidth) {
        this.editWidth = editWidth;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getSupplementMsg() {
        return supplementMsg;
    }

    public void setSupplementMsg(String supplementMsg) {
        this.supplementMsg = supplementMsg;
    }
}
