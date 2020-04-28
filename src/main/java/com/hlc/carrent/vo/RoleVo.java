package com.hlc.carrent.vo;

import com.hlc.carrent.domain.Role;
import lombok.Data;

public class RoleVo extends Role {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    //接收多个参数
    private Integer[] ids;


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
