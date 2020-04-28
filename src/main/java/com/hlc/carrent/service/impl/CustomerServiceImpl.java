package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.mapper.CustomerMapper;
import com.hlc.carrent.service.CustomerService;
import com.hlc.carrent.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> selectAllCustomer(CustomerVo customerVo) {
        PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        if (customerVo.getPage() == null) {
            customerVo.setPage(1);
        }
        if (customerVo.getLimit() == null) {
            customerVo.setPage(10);
        }
        return customerMapper.selectAllCustomer(customerVo);
    }

    @Override
    public int selectAllCustomerCount() {
        return customerMapper.selectAllCustomerCount();
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.addCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(String identity) {
        //删除客户表里的数据
        customerMapper.deleteCustomer(identity);
    }

    @Override
    public void deleteBatchCustomer(Integer[] ids) {
        for (Integer rid : ids) {
            deleteCustomer(Integer.toString(rid));
        }
    }

    @Override
    public Customer selectCustomerByIdentity(String identity) {
        return customerMapper.selectCustomerByIdentity(identity);
    }

    @Override
    public List<Customer> selectAllCustomerForLlist(CustomerVo customerVo) {
        return customerMapper.selectAllCustomer(customerVo);
    }

}
