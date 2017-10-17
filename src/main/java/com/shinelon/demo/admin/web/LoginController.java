package com.shinelon.demo.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinelon.demo.admin.bean.Msg;

/***
 *
 * @author syq
 *
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/")
    public String index() {
        logger.debug("login success");
        return "index";
    }

    /***
     * 登录页面跳转
     *
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        logger.debug("login...");
        return "login";
    }

    /***
     * 注册页面跳转
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "user/register";
    }

    /***
     * 登录成功页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/success")
    public String success(HttpServletRequest request, Model model) {
        logger.debug("Weclome...");
        Msg msg = new Msg("欢迎登陆", "通用消息", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "success";
    }
}
