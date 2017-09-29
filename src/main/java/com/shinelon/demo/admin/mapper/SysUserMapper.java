package com.shinelon.demo.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shinelon.demo.admin.entity.SysUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shinelon
 * @since 2017-09-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 分页查询用户角色信息
     * 
     * @param page
     * @return
     */

    List<SysUser> findAllByPage(Pagination page);

    /**
     * 查询用户角色信息
     *
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

}