package com.hlc.carrent.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台的控制
 */
@Controller
@RequestMapping("desk")
public class DeskController {

    /**
     * 跳转到工作台
     */
    @GetMapping("toDeskManager")
    public String toDeskManager() {
        return "system/main/deskManager";
    }
}
