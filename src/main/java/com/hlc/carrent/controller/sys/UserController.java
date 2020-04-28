package com.hlc.carrent.controller.sys;

import com.hlc.carrent.domain.User;
import com.hlc.carrent.service.UserService;
import com.hlc.carrent.utils.DataGridView;
import com.hlc.carrent.utils.ResultObj;
import com.hlc.carrent.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色管理控制器
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *  加载用户列表返回DataGridView
     */
    @ResponseBody
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        List<User> users = userService.selectAllUser(userVo);
        int count = userService.selectAllUserCount();
        DataGridView dataGridView = new DataGridView();
        dataGridView.setCount((long) count);
        dataGridView.setData(users);
        return dataGridView;
    }
    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            userService.addUser(userVo);
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
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id删除角色
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo userVo) {
        System.out.println(userVo);
        try {
            userService.deleteUser(userVo.getUserid());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @PostMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo) {
        try {
            userService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 重置用户密码
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @PostMapping("resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo) {
        try {
            userService.resetUserPwd(userVo.getUserid());
            return ResultObj.RESET_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 加载用户管理的分配角色数据
     */
    @ResponseBody
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVO){
        return userService.selectUserRole(userVO.getUserid());
    }
    /**
     *  保存用户和角色的关系
     */
    @ResponseBody
    @PostMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){
        try {
            userService.saveUserROle(userVo);
            return ResultObj.RESET_SUCCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }
}
