package com.hlc.carrent.service;

import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.vo.RentVo;

import java.util.List;

public interface RentService {

    /**
     * 查询所有出租单号信息
     */
    List<Rent> selectAllRent(RentVo RentVo);

    /**
     * 查询出租单的条数
     */
    int selectAllRentCount();

    /**
     * 保存出租单信息
     */
    void addRent(RentVo rentVo);
    /**
     * 修改出租单号
     */
    void updateRent(RentVo rentVo);
    /**
     * 删除出租单号
     */
    void deleteRent(String rentid);
    /**
     * 批量删除出组单号
     */
    void deleteBatchRent(Integer[] ids);

    /**
     * 根据出租单号查询出租信息
     */
    Rent selectRentByRentId(String rentid);
}
