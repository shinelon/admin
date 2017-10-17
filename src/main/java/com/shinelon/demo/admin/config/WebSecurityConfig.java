package com.shinelon.demo.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shinelon.demo.admin.security.CustomUserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/register").permitAll().antMatchers("/user/register")
                .permitAll().antMatchers("/static/**").permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/login").failureUrl("/login?error").permitAll().and().logout().permitAll().and().headers()
                .frameOptions().disable();

    }

    @Bean
    UserDetailsService customUserService() { // 注册UserDetailsService 的bean
        return new CustomUserServiceImpl();
    }
}
