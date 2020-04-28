package com.hlc.carrent.controller.sys;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.hlc.carrent.constast.SysConstast;
import com.hlc.carrent.domain.User;
import com.hlc.carrent.service.LogInfoService;
import com.hlc.carrent.service.UserService;
import com.hlc.carrent.utils.WebUtils;
import com.hlc.carrent.vo.LogInfoVo;
import com.hlc.carrent.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogInfoService logInfoService;
    /**
     * 跳转到登录页面
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/main/login";
    }

    /**
     * 登录方法
     */
    @PostMapping("login")
    public String login(UserVo userVo, Model model){
        //从session中获取保存的验证码
        String code = WebUtils.getHttpSession().getAttribute("code").toString();
        System.out.println(userVo);
        if(userVo.getCode().equals(code)){
            User user = this.userService.login(userVo);

            if (user != null){
                //放到session
                WebUtils.getHttpSession().setAttribute("user",user);
                //记录登录日志(像sys_login_log)里面插入数据
                LogInfoVo logInfoVo = new LogInfoVo();

                logInfoVo.setLogintime(new Date()); //设置当前时间
                logInfoVo.setLoginname(user.getRealname()+ "-" +user.getLoginname());
                logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr()); //设置登录IP地址

                logInfoService.addLogInfo(logInfoVo);
                return "system/main/index";
            }else {
                model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        }else if(userVo.getCode() == null && userVo.getCode() == ""){
            model.addAttribute("error",SysConstast.USER_LOGIN_CODE_ERROR_NULL);
            return "system/main/login";
        }else {
            model.addAttribute("error",SysConstast.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }


    }
    /**
     * 获得验证码
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse responsem, HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,34,4,5);
        //保存到session中
        session.setAttribute("code",lineCaptcha.getCode());
        //创建输出流
        ServletOutputStream outputStream = responsem.getOutputStream();
        //输出图片
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }

    /**
     * 退出登录
     */
    @RequestMapping("outLogin")
    public String outLogin(HttpSession session){
        session.removeAttribute("user");
        return "system/main/login";
    }
}
