package com.hlc.carrent.service.impl;

import com.hlc.carrent.domain.BaseEntiy;
import com.hlc.carrent.mapper.StatMapper;
import com.hlc.carrent.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper statMapper;

    @Override
    public List<BaseEntiy> loadCustomerAreaStatJsonList() {
        return statMapper.loadCustomerAreaStatJsonList();
    }

    @Override
    public List<BaseEntiy> loadOpernameYearGradeStat(String year) {
        return statMapper.loadOpernameYearGradeStat(year);
    }

    @Override
    public List<Double> loadComponyYearGradeStat(String year) {
        return statMapper.loadComponyYearGradeStat(year);
    }
}
