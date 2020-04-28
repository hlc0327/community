package com.hlc.carrent.service;

import com.hlc.carrent.domain.Check;
import com.hlc.carrent.vo.CheckVo;

import java.util.List;
import java.util.Map;


public interface CheckService {

    /**
     * 根据出租单号加载检查单的表单数据
     */
    Map<String,Object> initCheckFormData(String rentid);

    /**
     * 保存检查单数据
     * @param check
     */
    void saveCheck(Check check);


    /**
     * 查询检查单
     */
    List<Check> selectAllCheck(CheckVo checkVo);

    /**
     * 查询租单号的条数
     */
    int selectAllCheckCount();

    /**
     * 修改检查单数据
     */
    int updateCheck(Check check);
}
