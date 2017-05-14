package com.longhum.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
public class HomeController {
	@RequestMapping("/")
    public ModelAndView login(){
        return new ModelAndView("usercenter/login");
    }
}
