package com.hlc.carrent.controller.stat;

import com.hlc.carrent.domain.BaseEntiy;
import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.service.CustomerService;
import com.hlc.carrent.service.RentService;
import com.hlc.carrent.service.StatService;
import com.hlc.carrent.utils.excle.ExprotCustomerUtils;
import com.hlc.carrent.utils.excle.ExprotRentUtil;
import com.hlc.carrent.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析
 */
@Controller
@RequestMapping("stat")
public class StatCountroller {

    @Autowired
    private StatService statService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentService rentService;

    /**
     * 客户地区统计页面
     */

    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat() {
        return "stat/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     */
    @ResponseBody
    @RequestMapping("loadCustomerAreaStatJson")
    public List<BaseEntiy> loadCustomerAreaStatJson() {
        return statService.loadCustomerAreaStatJsonList();
    }

    /**
     * 跳转到业务统计的页面
     */
    @RequestMapping("toOpernameYearGradeStat")
    public String toOpernameYearGradeStat() {
        return "stat/opernameSalMoneyYearStat";
    }

    /**
     * 加载业务统计数据
     */
    @ResponseBody
    @RequestMapping("loadOpernameYearGradeStat")
    public Map<String, Object> loadOpernameYearGradeStat(String year) {
        List<BaseEntiy> entites = statService.loadOpernameYearGradeStat(year);
        Map<String, Object> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (BaseEntiy baseEntiys : entites) {
            names.add(baseEntiys.getName());
            values.add(String.valueOf(baseEntiys.getValue()));

        }
        map.put("name", names);
        map.put("values", values);

        return map;
    }


    /**
     * 跳转到业务统计的页面
     */
    @RequestMapping("toCompanyYearGradeStat")
    public String toCompanyYearGradeStat() {
        return "stat/companyYearGradeStat";
    }

    /**
     * 加载业务统计数据
     */
    @ResponseBody
    @RequestMapping("loadCompanyYearGradeStat")
    public List<Double> loadComponyYearGradeStat(String year) {
        List<Double> entities = statService.loadComponyYearGradeStat(year);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == null) {
                entities.set(i, 0.0);
            }
        }
        return entities;
    }

    /**
     * 导出客户数据
     */
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response) {
        List<Customer> customes = customerService.selectAllCustomerForLlist(customerVo);
        String fineName = "客户数据.xls";
        String sheetName = "客户数据";

        ByteArrayOutputStream bos = ExprotCustomerUtils.exportCustomer(customes, sheetName);

        try {
            fineName = URLEncoder.encode(fineName, "utf-8"); //处理文件乱码

            //创建封装相应头对象信息
            HttpHeaders header = new HttpHeaders();
            //封装相应内容类型
            header.setContentDispositionFormData("attachment", fineName);

            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出出租单信息
     */
    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid) {
        //根据出租单号查询出租单信息
        Rent rent = rentService.selectRentByRentId(rentid);
        //根据身份证号查询客户信息
        Customer customer = customerService.selectCustomerByIdentity(rent.getIdentity());

        String fineName = customer.getCustname()+"-出租单的信息.xls";
        String sheetName = customer.getCustname()+"出租单";

        ByteArrayOutputStream bos = ExprotRentUtil.exportRent(rent,customer, sheetName);

        try {
            fineName = URLEncoder.encode(fineName, "utf-8"); //处理文件乱码

            //创建封装相应头对象信息
            HttpHeaders header = new HttpHeaders();
            //封装相应内容类型
            header.setContentDispositionFormData("attachment", fineName);

            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
