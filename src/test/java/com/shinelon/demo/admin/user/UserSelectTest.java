package com.shinelon.demo.admin.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.plugins.Page;
import com.shinelon.demo.admin.AdminApplication;
import com.shinelon.demo.admin.entity.SysUser;
import com.shinelon.demo.admin.mapper.SysUserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSelectTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelect() {
        System.out.println(sysUserMapper.findByUserName("admin"));
    }

    @Test
    public void testSelectPage() {
        Page<SysUser> page = new Page<SysUser>(0, 1);
        System.out.println(sysUserMapper.findAllByPage(page));
    }
}
