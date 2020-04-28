package com.hlc.carrent.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.mapper.MenuMapper;
import com.hlc.carrent.service.MenuService;
import com.hlc.carrent.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuLeftTree(MenuVo menuVo) {
        return menuMapper.selectMenuLeftTree(menuVo);
    }

    @Override
    public List<Menu> selectMenuByUserIdForList(MenuVo menuVo, Integer id) {
        return null;
    }

    @Override
    public List<Menu> selectMenuRightTree(Menu menu) {
        return menuMapper.selectMenuRightTree(menu);
    }

    @Override
    public List<Menu> selectAllMenu(Integer pageNum, Integer pageSize, String title, Integer id,Integer available) {
        PageHelper.startPage(pageNum, pageSize);
        return menuMapper.selectAllMenu(title, id,available);
    }

    @Override
    public int selectAllMenuCount() {
        return menuMapper.selectAllMenuCount();
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public Integer selectMenuByPid(Integer pid) {
        return menuMapper.selectMenuByPid(pid);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        //删除菜单表里的数据
        menuMapper.deleteMenu(menuVo.getId());
        //根据菜单id删除sys_role_menu里面的数据
        menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }

    @Override
    public List<Menu> selectMenuByUid(Integer available, Integer userid) {
        return menuMapper.selectMenuByUid(available,userid);
    }

}
