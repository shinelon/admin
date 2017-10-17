package com.shinelon.demo.admin.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shinelon.demo.admin.entity.SysRole;
import com.shinelon.demo.admin.entity.SysUser;
import com.shinelon.demo.admin.mapper.SysUserMapper;

/***
 *
 * @author syq
 *
 */
public class CustomUserServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for (SysRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.debug("add role:{}", role.getName());
        }
        User securityUser = new User(user.getUsername(), user.getPassword(), authorities);
        logger.debug("securityUser:{}", securityUser);
        return securityUser;
    }

}
