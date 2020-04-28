import com.hlc.carrent.domain.Role;
import com.hlc.carrent.mapper.RoleMapper;
import com.hlc.carrent.service.RoleService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.vo.RoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class RoleTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;



    @Test
    public void testSelectUser() throws Exception {
        RoleVo roleVo = new RoleVo();
        roleVo.setPage(1);
        roleVo.setLimit(10);
        roleVo.setRolename("管理员");
        List<Role> roles = roleService.selectAllRole(roleVo);
        System.out.println(roles);
    }
    @Test
    public void testSelectUser2() throws Exception {
        DataGridView dataGridView = roleService.initRoleMenuTreeJson(null);
        System.out.println(dataGridView);

    }

}
