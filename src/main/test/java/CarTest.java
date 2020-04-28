import com.hlc.carrent.domain.Car;
import com.hlc.carrent.mapper.CarMapper;
import com.hlc.carrent.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class CarTest {

    @Autowired
    private CarMapper CarMapper;
    @Autowired
    private CarService CarService;



    @Test
    public void testSelectUser() throws Exception {
        Car car = new Car();
        car.setCarnumber("123");
        int i = CarMapper.updateCar(car);

        System.out.println(i);

    }
}
