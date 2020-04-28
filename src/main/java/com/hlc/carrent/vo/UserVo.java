package com.hlc.carrent.vo;

import com.hlc.carrent.domain.User;

public class UserVo extends User {


    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    //接收多个参数
    private Integer[] ids;

    //保存验证码
    private String code;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
