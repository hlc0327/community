package com.hlc.carrent.controller.bus;

import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.domain.User;
import com.hlc.carrent.service.CustomerService;
import com.hlc.carrent.service.RentService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.RandomUtils;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.utils.WebUtils;
import com.hlc.carrent.vo.RentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 出租单号管理控制器
 */
@Controller
@RequestMapping("rent")
public class RentController {


    @Autowired
    private RentService rentService;

    @Autowired
    private CustomerService customerService;


    /**
     * 检查身份证号是否存在
     */
    @ResponseBody
    @RequestMapping("checkCustomerExist")
    public ResultObj deleteCar(RentVo rentVo) {
        Customer customer = customerService.selectCustomerByIdentity(rentVo.getIdentity());
        if (customer != null){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单数据
     */
    @ResponseBody
    @RequestMapping("initRentFrom")
    public RentVo initRentFrom(RentVo rentVo){
        //生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        //设置起租时间
        rentVo.setBegindate(new Date());

        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        //设置操作员
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }
    /**
     * 保存出租单信息
     */
    @ResponseBody
    @RequestMapping("saveRent")
    public ResultObj saveRent(RentVo rentVo) {
        try {

            //设置创建时间
            rentVo.setCreatetime(new Date());
            //设置归还状态
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
            //保存
            System.out.println(rentVo);
            rentService.addRent(rentVo);

            return ResultObj.RENT_OUT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RENT_OUT_ERROR;
        }
    }


    /***************出租单管理*******************/
    /**
     * 出租单信息列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo) {

        List<Rent> list = rentService.selectAllRent(rentVo);
        int count = rentService.selectAllRentCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(list);
        return dataGridView;
    }


    /**
     * 修改出租单号
     */
    @ResponseBody
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo) {
        try {
            this.rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据Rentid删除出租单号
     * @param rentVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(RentVo rentVo) {
        try {
            rentService.deleteRent(rentVo.getRentid());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除出租单号
     * @param rentVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchRent")
    public ResultObj deleteBatchRent(RentVo rentVo) {
        try {
            rentService.deleteBatchRent(rentVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



}
