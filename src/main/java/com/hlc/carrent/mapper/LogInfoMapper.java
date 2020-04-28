package com.hlc.carrent.mapper;

import com.hlc.carrent.domain.LogInfo;
import com.hlc.carrent.vo.LogInfoVo;

import java.util.List;

public interface LogInfoMapper {


    /**
     * 查询所有日志
     */
    List<LogInfo> selectAllLogInfo(LogInfoVo logInfoVo);

    /**
     * 查询角色的条数
     */
    int selectAllLogInfoCount();

    /**
     * 添加日志
     */
    int addLogInfo(LogInfo logInfo);

    /**
     * 修改日志
     */
    int updateLogInfo(LogInfo logInfo);

    /**
     * 根据id删日志
     *
     */
    void deleteLogInfo(Integer logInfoId);

    /**
     * 批量删除角色
     */
    void deleteBatchLogInfo(Integer[] ids);

}
