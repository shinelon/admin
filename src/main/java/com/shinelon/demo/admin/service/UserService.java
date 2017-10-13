package com.shinelon.demo.admin.service;

import com.shinelon.demo.admin.bean.PageBean;
import com.shinelon.demo.admin.bean.RegisterBean;
import com.shinelon.demo.admin.entity.SysUser;

/***
 * 用户管理
 *
 * @author syq
 *
 */
public interface UserService {
    /***
     * 校验注册参数
     *
     * @param registerBean
     * @return
     */
    String checkRegisterBean(RegisterBean registerBean);

    /**
     * 用户查询分页
     *
     * @param page
     * @return
     */
    PageBean<SysUser> listPage(PageBean<SysUser> page);

    /***
     * 注册新用户
     * 
     * @param registerBean
     */
    void registerUser(RegisterBean registerBean);

    /***
     * 修改用户信息（不包含密码）
     *
     * @param sysUser
     * @throws Exception
     */
    void updateSysUserInfo(SysUser sysUser) throws Exception;

}
