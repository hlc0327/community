import com.hlc.carrent.domain.Car;
import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.mapper.CarMapper;
import com.hlc.carrent.mapper.CustomerMapper;
import com.hlc.carrent.mapper.RentMapper;
import com.hlc.carrent.service.CheckService;
import com.hlc.carrent.service.RentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class Check {


    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CarMapper carMapper;
    
    @Autowired
    private CheckService checkService;
    @Autowired
    private RentService rentService;
//    @Test
//    public void testSelectUser() throws Exception {
//
//        System.out.println(checkService.initCheckFormData("CZ_20180611_171304_0732_57330"));
//    }
    @Test
    public void testSelectUser1() throws Exception {
        //查询出租单
        Rent rent = rentMapper.selectRentByRentId("CZ_20180611_171304_0732_57330");
        System.out.println(rent);
        Customer customer = customerMapper.selectCustomerByIdentity(rent.getIdentity());
        System.out.println(customer);
        //查询车辆
        Car car = carMapper.selectCarByCarNumber(rent.getCarnumber());
        System.out.println(car);
    }
    @Test
    public void testSelectUser2() throws Exception {
        checkService.initCheckFormData("CZ_20180611_171304_0732_57330");
    }
    @Test
    public void testSelectUser3() throws Exception {
        Rent rent = rentService.selectRentByRentId("xxx");
        System.out.println(rent);
    }
}
