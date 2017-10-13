package com.shinelon.demo.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shinelon.demo.admin.bean.PageBean;
import com.shinelon.demo.admin.entity.SysUser;
import com.shinelon.demo.admin.mapper.SysUserMapper;
import com.shinelon.demo.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageBean<SysUser> listPage(PageBean<SysUser> page) {
        Pagination pageParm = getPage(page.getOrder(), page.getOffset(), page.getLimit());
        List<SysUser> userList = sysUserMapper.findAllByPage(pageParm);
        page.setRows(userList);
        page.setTotal(pageParm.getTotal());
        return page;
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
