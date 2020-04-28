package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.*;
import com.hlc.carrent.mapper.CarMapper;
import com.hlc.carrent.mapper.CheckMapper;
import com.hlc.carrent.mapper.CustomerMapper;
import com.hlc.carrent.mapper.RentMapper;
import com.hlc.carrent.service.CheckService;
import com.hlc.carrent.utils.RandomUtils;
import com.hlc.carrent.utils.WebUtils;
import com.hlc.carrent.vo.CheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CheckMapper checkMapper;

    @Override
    public Map<String, Object> initCheckFormData(String rentid) {

        //查询出租单
        Rent rent = rentMapper.selectRentByRentId(rentid);
        System.out.println(rentid);
        //查询客户
        Customer customer = customerMapper.selectCustomerByIdentity(rent.getIdentity());
        //查询车辆
        Car car = carMapper.selectCarByCarNumber(rent.getCarnumber());
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        //获取操作员
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());

        Map<String, Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);
        return map;
    }

    @Override
    public void saveCheck(Check check) {
        checkMapper.saveCheck(check);
    }

    @Override
    public List<Check> selectAllCheck(CheckVo checkVo) {
        PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        if (checkVo.getPage() == null) {
            checkVo.setPage(1);
        }
        if (checkVo.getLimit() == null) {
            checkVo.setPage(10);
        }
        return checkMapper.selectAllCheck(checkVo);
    }

    @Override
    public int selectAllCheckCount() {
        return checkMapper.selectAllCheckCount();
    }

    @Override
    public int updateCheck(Check check) {
        return checkMapper.updateCheck(check);
    }


}
