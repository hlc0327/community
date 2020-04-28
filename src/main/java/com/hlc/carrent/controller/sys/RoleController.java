package com.hlc.carrent.controller.sys;

import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.Role;
import com.hlc.carrent.service.RoleService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.vo.DtreeVo;
import com.hlc.carrent.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * 角色管理控制器
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 加载右边角色列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo) {

        List<Role> list = roleService.selectAllRole(roleVo);
        System.out.println(roleVo);
        int count = roleService.selectAllRoleCount();

        DataGridView dataGridView = new DataGridView();
        dataGridView.setData(list);
        return dataGridView;
    }
    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo) {
        try {
            roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改角色
     */
    @ResponseBody
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo) {
        System.out.println(roleVo);
        try {
            roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id删除角色
     *
     * @param roleVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(RoleVo roleVo) {
        System.out.println(roleVo);
        try {
            roleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除角色
     * @param roleVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo) {
        try {
            roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的json
     */
    @ResponseBody
    @RequestMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid){

        return roleService.initRoleMenuTreeJson(roleid);
    }


    /**
     * 保存角色和菜单的关系
     */
    @ResponseBody
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleRoleMenu(RoleVo roleVo){
        Integer rid = roleVo.getRoleid();
        Integer[] ids = roleVo.getIds();
        for (Integer mid : ids){
            System.out.println(mid +"--"+ rid);
        }
        try {

            roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }


}
