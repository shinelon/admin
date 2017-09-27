package com.shinelon.demo.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinelon.demo.admin.bean.Msg;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        logger.debug("login...");
        return "login";
    }

    @RequestMapping("/")
    public String success(HttpServletRequest request, Model model) {
        logger.debug("login success");
        Msg msg = new Msg("测试标题", "普通用户测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "success";
    }
}
