package com.hlc.carrent.vo;

import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.domain.News;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CustomerVo extends Customer {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    //接收多个id
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
