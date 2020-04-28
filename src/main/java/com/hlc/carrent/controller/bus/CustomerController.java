package com.hlc.carrent.controller.bus;

import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.service.CustomerService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 客户管理控制器
 */
@Controller
@RequestMapping("customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    /**
     * 客户列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {

        List<Customer> list = customerService.selectAllCustomer(customerVo);
        System.out.println(customerVo);
        int count = customerService.selectAllCustomerCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(list);
        return dataGridView;
    }
    /**
     * 添加客户
     */
    @ResponseBody
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo) {
        try {
            customerVo.setCreatetime(new Date());   //设置时间
            customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改客户
     */
    @ResponseBody
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo) {
        System.out.println(customerVo);
        try {
            customerVo.setCreatetime(new Date());   //设置时间
            customerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id删除客户
     * @param customerVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(CustomerVo customerVo) {
        try {
            customerService.deleteCustomer(customerVo.getIdentity());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除客户
     * @param customerVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerVo customerVo) {
        try {
            customerService.deleteBatchCustomer(customerVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



}
