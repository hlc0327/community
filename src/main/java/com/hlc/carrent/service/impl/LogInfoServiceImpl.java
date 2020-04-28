package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.domain.LogInfo;
import com.hlc.carrent.mapper.MenuMapper;
import com.hlc.carrent.mapper.LogInfoMapper;
import com.hlc.carrent.service.LogInfoService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.vo.DtreeVo;
import com.hlc.carrent.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public List<LogInfo> selectAllLogInfo(LogInfoVo logInfoVo) {
        PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
        if (logInfoVo.getPage() == null) {
            logInfoVo.setPage(1);
        }
        if (logInfoVo.getLimit() == null) {
            logInfoVo.setPage(10);
        }
        return logInfoMapper.selectAllLogInfo(logInfoVo);
    }

    @Override
    public int selectAllLogInfoCount() {
        return logInfoMapper.selectAllLogInfoCount();
    }

    @Override
    public int addLogInfo(LogInfo logInfo) {
        return logInfoMapper.addLogInfo(logInfo);
    }

    @Override
    public int updateLogInfo(LogInfo logInfo) {
        return logInfoMapper.updateLogInfo(logInfo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoid) {
        System.out.println(logInfoid);
        //删除菜单表里的数据
        logInfoMapper.deleteLogInfo(logInfoid);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer rid : ids) {
            deleteLogInfo(rid);
        }
    }



}
