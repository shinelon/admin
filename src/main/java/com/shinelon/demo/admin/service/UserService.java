package com.shinelon.demo.admin.service;

import com.shinelon.demo.admin.bean.PageBean;
import com.shinelon.demo.admin.entity.SysUser;

/***
 * 用户管理
 *
 * @author syq
 *
 */
public interface UserService {
    /**
     * 用户查询分页
     *
     * @param page
     * @return
     */
    PageBean<SysUser> listPage(PageBean<SysUser> page);

    /***
     * 修改用户信息（不包含密码）
     * 
     * @param sysUser
     * @throws Exception
     */
    void updateSysUserInfo(SysUser sysUser) throws Exception;
}
