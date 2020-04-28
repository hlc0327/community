import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.domain.Rent;
import com.hlc.carrent.service.CustomerService;
import com.hlc.carrent.service.RentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class RentTest {

    @Autowired
    private RentService rentService;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testSelectUser() throws Exception {

    }

    @Test
    public void testSelectUser3() throws Exception {
        Rent rent = rentService.selectRentByRentId("CZ_20180611_171304_0732_57330");
        System.out.println(rent);
        Customer customer = customerService.selectCustomerByIdentity(rent.getIdentity());
        System.out.println(customer);
    }
}
