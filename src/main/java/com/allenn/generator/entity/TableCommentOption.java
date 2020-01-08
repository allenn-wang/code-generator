package com.allenn.generator.entity;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class TableCommentOption {
    /*
     * 业务注释格式: <表名>(!<业务属性json对象>)
     *   eg. 考试表!{"cacheEnable":"true","singleCacheExpire":"1d","listCacheExpire":"0" }
     **/

    private boolean cacheEnable = false;

    private Integer singleCacheExpire = 5 * 60;

    private String singleCacheUnit = TimeUnit.SECONDS.name();

    private Integer listCacheExpire = 5 * 60;

    private String listCacheUnit = TimeUnit.SECONDS.name();

    public boolean isCacheEnable() {
        return cacheEnable;
    }

    public void setCacheEnable(boolean cacheEnable) {
        this.cacheEnable = cacheEnable;
    }

    public Integer getSingleCacheExpire() {
        return singleCacheExpire;
    }

    public void setSingleCacheExpire(Integer singleCacheExpire) {
        this.singleCacheExpire = singleCacheExpire;
    }

    public String getSingleCacheUnit() {
        return singleCacheUnit;
    }

    public void setSingleCacheUnit(String singleCacheUnit) {
        this.singleCacheUnit = singleCacheUnit;
    }

    public Integer getListCacheExpire() {
        return listCacheExpire;
    }

    public void setListCacheExpire(Integer listCacheExpire) {
        this.listCacheExpire = listCacheExpire;
    }

    public String getListCacheUnit() {
        return listCacheUnit;
    }

    public void setListCacheUnit(String listCacheUnit) {
        this.listCacheUnit = listCacheUnit;
    }
}
