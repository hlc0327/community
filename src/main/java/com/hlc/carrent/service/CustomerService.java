package com.hlc.carrent.service;

import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.vo.CustomerVo;

import java.util.List;

public interface CustomerService {

    /**
     * 查询所有客户
     */
    List<Customer> selectAllCustomer(CustomerVo customerVo);

    /**
     * 查询角色的条数
     */
    int selectAllCustomerCount();

    /**
     * 添加客户
     */
    int addCustomer(Customer customer);

    /**
     * 修改客户
     */
    int updateCustomer(Customer customer);

    /**
     * 根据id删客户
     */
    void deleteCustomer(String identity);

    /**
     * 批量删除角色
     */
    void deleteBatchCustomer(Integer[] ids);

    /**
     * 根据身份证查询客户信息
     */
    public Customer selectCustomerByIdentity(String identity);

    /**
     * 查询客户数据返回集合
     * @param customerVo
     * @return
     */
    List<Customer> selectAllCustomerForLlist(CustomerVo customerVo);
}
