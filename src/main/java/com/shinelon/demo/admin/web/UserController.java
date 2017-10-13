package com.shinelon.demo.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinelon.demo.admin.bean.PageBean;
import com.shinelon.demo.admin.bean.RegisterBean;
import com.shinelon.demo.admin.entity.SysUser;
import com.shinelon.demo.admin.service.UserService;
import com.shinelon.demo.admin.utils.JsonFormatUtil;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 用户管理页面
     *
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return "/user/list";
    }

    /**
     * 分页查询用户数据
     *
     * @param parms
     * @return
     */
    @RequestMapping("/listPage")
    @ResponseBody
    public String listPage(PageBean<SysUser> parms) {
        logger.debug("parms:{}", parms);
        PageBean<SysUser> page = userService.listPage(parms);
        return JsonFormatUtil.getSuccessJson(page).toJSONString();
    }

    /***
     * 注册新用户
     *
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model, HttpServletRequest request, RegisterBean registerBean) {
        logger.info("register:{}", registerBean);
        String checkRet = userService.checkRegisterBean(registerBean);
        if (!StringUtils.isEmpty(checkRet)) {
            model.addAttribute("error", checkRet);
            return "/user/register";
        }
        userService.registerUser(registerBean);
        model.addAttribute("success", "注册成功！");
        return "/user/register";
    }

    /***
     * 修改用户基本信息（不包含密码）
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateSysUserInfo(SysUser sysUser) {
        try {
            userService.updateSysUserInfo(sysUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonFormatUtil.getFailureJson(e.getMessage()).toJSONString();
        }
        return JsonFormatUtil.getSuccessJson().toJSONString();
    }
}
