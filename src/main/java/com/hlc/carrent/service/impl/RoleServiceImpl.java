package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.domain.Role;
import com.hlc.carrent.mapper.MenuMapper;
import com.hlc.carrent.mapper.RoleMapper;
import com.hlc.carrent.service.RoleService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.vo.DtreeVo;
import com.hlc.carrent.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> selectAllRole(RoleVo roleVo) {
        PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        if (roleVo.getPage() == null) {
            roleVo.setPage(1);
        }
        if (roleVo.getLimit() == null) {
            roleVo.setPage(10);
        }
        return roleMapper.selectAllRole(roleVo);
    }

    @Override
    public int selectAllRoleCount() {
        return roleMapper.selectAllRoleCount();
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(Integer roleid) {
        System.out.println(roleid);
        //删除菜单表里的数据
        roleMapper.deleteRole(roleid);
        //根据菜单id删除sys_role_menu里面的数据
        roleMapper.deleteRoleMenuByRid(roleid);
        //根据菜单id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByRid(roleid);

    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        // 根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE, roleid);

        List<DtreeVo> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO + "";
            for (Menu m2 : roleMenu) {
                if (m1.getId() == m2.getId()) {
                    checkArr = SysConstast.CODE_ONE + "";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            String spread2 = m1.getSpread2();
            boolean spread = true;
            if (spread2.equals("1")) {
                spread = true;
            } else if (spread2.equals("0")) {
                spread = false;
            }

            data.add(new DtreeVo(id, pid, title, spread, checkArr));
        }

        return new DataGridView(data);

    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid = roleVo.getRoleid();
        Integer[] ids = roleVo.getIds();

        //先根据rid删除sys_role_menu里面的所有数据
        roleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : ids){
            roleMapper.saveRoleMenu(rid,mid);
        }
    }


}
