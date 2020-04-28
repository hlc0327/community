import com.hlc.carrent.domain.BaseEntiy;
import com.hlc.carrent.mapper.StatMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class Stat {


    @Autowired
    private StatMapper statMapper;

    @Test
    public void testSelectUser1() throws Exception {
        List<BaseEntiy> baseEntiys = statMapper.loadCustomerAreaStatJsonList();
        System.out.println(baseEntiys);
    }
}
