package com.hlc.carrent.controller.bus;

import com.hlc.carrent.domain.Check;
import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.service.CheckService;
import com.hlc.carrent.service.RentService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.vo.CheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 汽车入库控制器
 */
@Controller
@RequestMapping("check")
public class CheckController {


    @Autowired
    private CheckService checkService;
    @Autowired
    private RentService rentService;

    /**
     * 根据出租单号查询出租信息
     */
    @ResponseBody
    @RequestMapping("checkRentExist")
    public Rent checkRentExist(String rentid){
        System.out.println(rentid);
        Rent rent = rentService.selectRentByRentId(rentid);
        return rent;
    }

    /**
     * 根据出租单号查询出租信息
     */
    @ResponseBody
    @RequestMapping("initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){

        return checkService.initCheckFormData(rentid);
    }

    /**
     * 根据出租单号保存出租信息
     */
    @ResponseBody
    @RequestMapping("saveCheck")
    public ResultObj saveCheck(Check check){
        try {
            check.setCreatetime(new Date());
            checkService.saveCheck(check);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 检查单列表
     */
    @ResponseBody
    @RequestMapping("loadAllCheck")
    public DataGridView initCheckFormData(CheckVo checkVo){

        List<Check> list = checkService.selectAllCheck(checkVo);

        int count = checkService.selectAllCheckCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(list);

        return dataGridView;
    }

    /**
     * 修改车辆
     */
    @ResponseBody
    @RequestMapping("updateCheck")
    public ResultObj updateCheck(CheckVo checkVo) {
        try {
            checkVo.setCreatetime(new Date());
            this.checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
