package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.Check;

import java.util.List;

public interface CheckMapper {

    /**
     * 查询
     */
    List<Check> selectAllCheck(Check check);

    /**
     * 查询租单号的条数
     */
    int selectAllCheckCount();

    /**
     * 保存检查单数据
     * @param check
     */
    void saveCheck(Check check);

    /**
     * 修改检查单数据
     */
    int updateCheck(Check check);
}

