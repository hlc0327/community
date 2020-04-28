package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.vo.RentVo;

import java.util.List;

public interface RentMapper {

    /**
     * 查询所有租单号信息
     */
    List<Rent> selectAllRent(RentVo RentVo);

    /**
     * 查询租单号的条数
     */
    int selectAllRentCount();

    /**
     * 保存出租单号
     * @param rentVo
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
    void deleteBatchCar(Integer[] ids);
    /**
     * 根据出租单号查询出租信息
     */
    Rent selectRentByRentId(String rentid);

}
