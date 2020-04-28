package com.hlc.carrent.controller.sys;

import com.hlc.carrent.domain.LogInfo;
import com.hlc.carrent.service.LogInfoService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 日志管理控制器
 */
@Controller
@RequestMapping("logInfo")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 日志列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo) {

        List<LogInfo> list = logInfoService.selectAllLogInfo(logInfoVo);
        System.out.println(logInfoVo);
        int count = logInfoService.selectAllLogInfoCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(list);
        return dataGridView;
    }
    /**
     * 添加日志
     */
    @ResponseBody
    @RequestMapping("addLogInfo")
    public ResultObj addLogInfo(LogInfoVo logInfoVo) {
        try {
            logInfoService.addLogInfo(logInfoVo);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改日志
     */
    @ResponseBody
    @RequestMapping("updateLogInfo")
    public ResultObj updateLogInfo(LogInfoVo logInfoVo) {
        System.out.println(logInfoVo);
        try {
            logInfoService.updateLogInfo(logInfoVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id删除日志
     *
     * @param logInfoVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(LogInfoVo logInfoVo) {
        System.out.println(logInfoVo);
        try {
            logInfoService.deleteLogInfo(logInfoVo.getId());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除日志
     * @param logInfoVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo) {
        try {
            logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



}
