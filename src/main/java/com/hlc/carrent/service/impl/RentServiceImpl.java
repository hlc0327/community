
package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Car;
import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.mapper.CarMapper;
import com.hlc.carrent.mapper.RentMapper;
import com.hlc.carrent.service.RentService;
import com.hlc.carrent.vo.RentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Rent> selectAllRent(RentVo rentVo) {
        PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        if (rentVo.getPage() == null) {
            rentVo.setPage(1);
        }
        if (rentVo.getLimit() == null) {
            rentVo.setPage(10);
        }
        PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());

        return rentMapper.selectAllRent(rentVo);
    }

    @Override
    public int selectAllRentCount() {
        return rentMapper.selectAllRentCount();
    }

    @Override
    public void addRent(RentVo rentVo) {
        rentMapper.addRent(rentVo);

        //更改车辆的出租状态
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_TRUE);

        carMapper.updateCarIsrentingByCarNumber(car);
    }

    @Override
    public void updateRent(RentVo rentVo) {
        rentMapper.updateRent(rentVo);
    }

    @Override
    public void deleteRent(String rentid) {
        rentMapper.deleteRent(rentid);
    }

    @Override
    public void deleteBatchRent(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRent(Integer.toString(rid));
        }
    }

    @Override
    public Rent selectRentByRentId(String rentid) {
        return rentMapper.selectRentByRentId(rentid);
    }


}


