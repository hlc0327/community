package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Role;
import com.hlc.carrent.domain.User;
import com.hlc.carrent.mapper.RoleMapper;
import com.hlc.carrent.mapper.UserMapper;
import com.hlc.carrent.service.UserService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.vo.RoleVo;
import com.hlc.carrent.vo.UserVo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> selectAllUser(UserVo userVo) {
        PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        if (userVo.getPage() == null) {
            userVo.setPage(1);
        }
        if (userVo.getLimit() == null) {
            userVo.setPage(10);
        }
        return userMapper.selectAllUser(userVo);

    }
    @Override
    public int selectAllUserCount() {
        return userMapper.selectAllUserCount();
    }

    @Override
    public int addUser(User user) {
        //设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //设置用户类型 -都是普通用户type=2
        user.setType(SysConstast.USER_TYPE_NORMAL);
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        //设置用户类型 -都是普通用户type=2
        user.setType(SysConstast.USER_TYPE_NORMAL);
        return userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer userid) {
        //删除用户
        userMapper.deleteUser(userid);
        //根据用户ID删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByRid(userid);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for(Integer uid :ids){
            this.deleteUser(uid);
        }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //设置默认密码
        userMapper.resetUserPwd(user.getPwd(),userid);
    }

    @Override
    public DataGridView selectUserRole(Integer userid) {
        //查询可用的角色
        RoleVo roleVo = new RoleVo();
        roleVo.setAvailable(SysConstast.AVAILABLE_TRUE);  //可用状态
        List<Role> allRole = roleMapper.selectAllRole(roleVo);
        //根据用户id查询已拥有的角色
        List<Role> userRole = roleMapper.selectRoleByUid(SysConstast.AVAILABLE_TRUE, userid);

        List<Map<String,Object>> data = new ArrayList<>();
        for(Role r1 : allRole){
            Boolean LAY_CHECKED = false;
            for (Role r2 : userRole){
                if(r1.getRoleid() == r2.getRoleid()){
                    LAY_CHECKED = true;
                }
            }

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);

        }
        DataGridView dataGridView = new DataGridView();
        dataGridView.setData(data);
        return dataGridView;
    }

    @Override
    public User login(UserVo userVo) {
        //名文123456
        //生成密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    @Override
    public void saveUserROle(UserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();

        //根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);

        //保存关系
        if (roleIds != null && roleIds.length > 0 ){
            for (Integer rid : roleIds){
                userMapper.insertUserRole(userid,rid);
            }
        }
    }


}
