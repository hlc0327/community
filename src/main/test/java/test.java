import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.mapper.UserMapper;
import com.hlc.carrent.service.RentService;
import com.hlc.carrent.service.UserService;
import com.hlc.carrent.utils.RandomUtils;
import com.hlc.carrent.vo.RentVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class test {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @Autowired
    private RentService rentMapper;

    @Test
    public void testSelectUser() throws Exception {
        String oldName = "002.jpg";
        String test = "_temp";
        String newName = RandomUtils.createFileNameUseTime(oldName, SysConstast.FILE_UPLOAD_TEMP);

        System.out.println(newName);
    }
    @Test
    public void testSelectUse1r() throws Exception {
        String oldName = "dd.jpeg";

        String fileSuffix=oldName.substring(oldName.lastIndexOf("."),oldName.length());

        System.out.println(fileSuffix);
    }
    @Test
    public void test() throws Exception {
        String oldName = "dd.jpeg";

        String fileSuffix=oldName.substring(oldName.lastIndexOf("."),oldName.length());

        System.out.println(fileSuffix);
    }
    @Test
    public void test1() throws Exception {
        String fileNameUseTime = RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC);
        System.out.println(fileNameUseTime);
    }
    @Test
    public void test2() throws Exception {
        RentVo rent = new RentVo();
        rent.setPage(1);
        rent.setLimit(10);
        rentMapper.selectAllRent(rent);
    }
}
