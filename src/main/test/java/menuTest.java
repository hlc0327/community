import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.mapper.MenuMapper;
import com.hlc.carrent.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class menuTest {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;


    @Test
    public void testSelectUser() throws Exception {
//        Menu menuVo = new Menu();
//        menuVo.setId(null);
//        menuVo.setTitle("");
//        List<Menu> menus = menuService.selectAllMenu(1,10,menuVo.getTitle(),menuVo.getId());
//        System.out.println(menus);
        List<Menu> allMenu = menuMapper.selectAllMenu(null, null, 1);
        System.out.println(allMenu);
    }
    @Test
    public void testSelectUser2() throws Exception {
        List<Menu> menus = menuMapper.queryAllMenu(null);
        System.out.println(menus);
    }

    @Test
    public void testSelectUser3() throws Exception {
//        List<Menu> menus = menuMapper.selectMenuLeftTree(null);
        Menu menu = new Menu();
        menu.setId(5);
        List<Menu> menus1 = menuMapper.selectMenuRightTree(menu);



        System.out.println(menus1);
    }

    @Test
    public void testSelectUser4() throws Exception {
        List<Menu> menus = menuMapper.selectMenuRightTree(null);
        System.out.println(menus);
    }

}
