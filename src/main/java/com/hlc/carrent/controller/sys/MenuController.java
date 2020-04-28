package com.hlc.carrent.controller.sys;

import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Menu;
import com.hlc.carrent.domain.User;
import com.hlc.carrent.service.MenuService;
import com.hlc.carrent.utils.*;
import com.hlc.carrent.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理控制器
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> Menu(MenuVo menuVo) {

        //得到当前登录的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        List<Menu> list = null; //创建一个空的列表

        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        if (user.getType() == SysConstast.USER_TYPE_SUPER) {  //用户类型(1: 代表超级管理员,2: 普通用户)
            list = this.menuService.selectMenuLeftTree(null);   //条件为空
        } else {
//            list = this.menuService.selectMenuByUserIdForList();  //后面修改
            MenuVo menuVo1 = new MenuVo();
            menuVo1.setId(user.getUserid());            //设置当前登录用户id
            menuVo1.setAvailable(user.getAvailable());  //设置当前登录用户菜单是否可用
            list = this.menuService.selectMenuLeftTree(menuVo1);  //后面修改
        }

        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            boolean spread = true;
            if (menu.getSpread2().equals("1")) {
                spread = true;
            } else if (menu.getSpread2().equals("0")) {
                spread = false;
            }

            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return TreeNodeBuilder.builder(nodes, 1);
    }

    /**
     * 加载菜单管理右边的菜单树
     */
    @ResponseBody
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView LoadMenuManagerLeftTreeJson(MenuVo menuVo) {
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的

        List<Menu> list = menuService.selectMenuRightTree(menuVo);
        DataGridView dataGridView = new DataGridView();
        dataGridView.setData(list);
        return dataGridView;
    }

    /**
     * 加载右边菜单列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo) {
        if (menuVo.getTitle() == null) {
            menuVo.setTitle("");
        }
        List<Menu> list = menuService.selectAllMenu(menuVo.getPage(), menuVo.getLimit(), menuVo.getTitle(), menuVo.getId(), menuVo.getAvailable());
        System.out.println(menuVo);
        int count = menuService.selectAllMenuCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setData(list);
        dataGridView.setCount((long) count);
        return dataGridView;
    }

    /**
     * 添加菜单
     */
    @ResponseBody
    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo) {
        System.out.println(menuVo);
        try {
            menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     */
    @ResponseBody
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo) {
        System.out.println(menuVo);
        try {
            menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code >= 0
     * 没有返回code < 0
     */
    @ResponseBody
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo) {
        //根据pid查询菜单数量
        Integer count = this.menuService.selectMenuByPid(menuVo.getId());
        if (count > 0) {
            return ResultObj.STATUS_TRUE;   //返回 0
        } else {
            return ResultObj.STATUS_FALSE;  //返回 -1
        }
    }

    /**
     * 根据id删除菜单
     *
     * @param menuVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo) {
        try {
            menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
