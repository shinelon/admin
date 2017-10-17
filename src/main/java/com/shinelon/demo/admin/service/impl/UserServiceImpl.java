package com.shinelon.demo.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shinelon.demo.admin.bean.PageBean;
import com.shinelon.demo.admin.bean.RegisterBean;
import com.shinelon.demo.admin.entity.SysUser;
import com.shinelon.demo.admin.entity.SysUserRole;
import com.shinelon.demo.admin.enums.Status;
import com.shinelon.demo.admin.mapper.SysUserMapper;
import com.shinelon.demo.admin.mapper.SysUserRoleMapper;
import com.shinelon.demo.admin.service.UserService;

/***
 *
 * @author syq
 *
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public String checkRegisterBean(RegisterBean registerBean) {
        if (!registerBean.getPassword().equals(registerBean.getConfirm())) {
            return "两次密码不一致请重新输入！";
        }
        List<SysUser> list = sysUserMapper
                .selectList(new EntityWrapper<SysUser>().eq("username", registerBean.getUsername()));
        if (list.size() > 0) {
            return "用户名已经被注册请重新输入！";
        }
        return null;
    }

    @Override
    public PageBean<SysUser> listPage(PageBean<SysUser> page) {
        Pagination pageParm = getPage(page.getOrder(), page.getOffset(), page.getLimit());
        List<SysUser> userList = sysUserMapper.findAllByPage(pageParm);
        page.setRows(userList);
        page.setTotal(pageParm.getTotal());
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(RegisterBean registerBean) {
        SysUser entity = new SysUser();
        entity.setUsername(registerBean.getUsername());
        entity.setPassword(new BCryptPasswordEncoder().encode(registerBean.getPassword()));
        entity.setStatus(Status.AVAILABLE.getValue());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setMobile(0);
        sysUserMapper.insert(entity);

        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(2L);
        userRole.setUserId(entity.getId());
        userRole.setCreateTime(new Date());
        userRole.setStatus(Status.AVAILABLE.getValue());
        sysUserRoleMapper.insert(userRole);
    }

    @Override
    public void updateSysUserInfo(SysUser sysUser) throws Exception {
        sysUserMapper.updateById(sysUser);
    }

    private Page<SysUser> getPage(String order, Integer offset, Integer limit) {
        int offsetInt = offset.intValue();
        int limitInt = limit.intValue();
        int pageNum = (offsetInt / limitInt) + 1;
        Page<SysUser> page = new Page<SysUser>(pageNum, limitInt);
        page.setOrderByField("id");
        page.setAsc("ASC".equalsIgnoreCase(order));
        logger.debug("pageConfig:{}", page);
        return page;
    }
}
