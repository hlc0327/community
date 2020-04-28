package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.Car;
import com.hlc.carrent.vo.CarVo;

import java.util.List;

public interface CarMapper {


    /**
     * 查询所有车辆
     */
    List<Car> selectAllCar(CarVo carVo);

    /**
     * 查询角色的条数
     */
    int selectAllCarCount();

    /**
     * 添加车辆
     */
    int addCar(Car car);

    /**
     * 修改车辆
     */
    int updateCar(Car car);

    /**
     * 根据carnumber删车辆
     *
     */
    void deleteCar(String carNumber);

    /**
     * 批量删除车辆
     */
    void deleteBatchCar(Integer[] ids);

    /**
     * 根据carnumber查询
     */
    Car selectCarByCarNumber(String carNumber);

    /**
     * 根据carnumber修改出租状态
     */
    void updateCarIsrentingByCarNumber(Car car);
}
