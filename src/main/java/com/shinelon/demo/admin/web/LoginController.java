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

    @RequestMapping("/")
    public String index() {
        logger.debug("login success");
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        logger.debug("login...");
        return "login";
    }

    @RequestMapping("/success")
    public String success(HttpServletRequest request, Model model) {
        logger.debug("Weclome...");
        Msg msg = new Msg("欢迎登陆", "通用消息", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "success";
    }
}
