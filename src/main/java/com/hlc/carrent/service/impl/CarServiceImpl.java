package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Car;
import com.hlc.carrent.mapper.CarMapper;
import com.hlc.carrent.service.CarService;
import com.hlc.carrent.utils.AppFileUtils;
import com.hlc.carrent.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> selectAllCar(CarVo carVo) {
        PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        if (carVo.getPage() == null) {
            carVo.setPage(1);
        }
        if (carVo.getLimit() == null) {
            carVo.setPage(10);
        }
        return carMapper.selectAllCar(carVo);
    }

    @Override
    public int selectAllCarCount() {
        return carMapper.selectAllCarCount();
    }

    @Override
    public int addCar(Car car) {
        return carMapper.addCar(car);
    }

    @Override
    public int updateCar(Car car) {
        return carMapper.updateCar(car);
    }

    @Override
    public void deleteCar(String carNumber) {
        //先删除图片
        Car car = carMapper.selectCarByCarNumber(carNumber);
        //如果不是默认图片就删除
        if(!car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        //删除数据库的数据
        carMapper.deleteCar(carNumber);
    }

    @Override
    public void deleteBatchCar(Integer[] ids) {
        for (Integer rid : ids) {
            deleteCar(Integer.toString(rid));
        }
    }

    @Override
    public Car selectCarByCarNumber(String carNumber) {
        return carMapper.selectCarByCarNumber(carNumber);
    }


}
