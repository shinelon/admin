package com.shinelon.demo.admin.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/***
 *
 * @author syq
 *
 */
public class RegisterBean {
    private String username;
    private String password;
    private String confirm;

    public String getConfirm() {
        return confirm;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
