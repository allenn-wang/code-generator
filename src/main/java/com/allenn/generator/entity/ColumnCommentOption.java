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

    // 业务类型为join时, 外键关联的表名
    private String joinTable;

    private Map<String, String> allowValues;

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

    public String getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable;
    }

    public Map<String, String> getAllowValues() {
        return allowValues;
    }

    public void setAllowValues(Map<String, String> allowValues) {
        this.allowValues = allowValues;
    }
}
