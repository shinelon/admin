package com.shinelon.demo.admin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinelon.demo.admin.bean.PageBean;
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
}
