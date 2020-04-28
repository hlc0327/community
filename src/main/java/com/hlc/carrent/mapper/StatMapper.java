package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.BaseEntiy;

import java.util.List;

public interface StatMapper {

    /**
     * 查询客户地区的数据
     *
     * @return
     */
    List<BaseEntiy> loadCustomerAreaStatJsonList();

    /**
     * 查询业务员年度统计数据
     *
     * @return
     */
    List<BaseEntiy> loadOpernameYearGradeStat(String year);

    /**
     * 加载公司年份统计数据
     *
     * @param year
     * @return
     */
    List<Double> loadComponyYearGradeStat(String year);
}
