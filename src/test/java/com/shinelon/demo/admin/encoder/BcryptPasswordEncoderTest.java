package com.shinelon.demo.admin.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shinelon.demo.admin.utils.Md5Util;

public class BcryptPasswordEncoderTest {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoder:  " + encoder.encode("abel"));
        System.out.println("encoder:  " + encoder.encode("abel").length());
        System.out.println("encoder:  " + encoder.encode("admin"));

        if (encoder.matches("abel", "$2a$10$IAz6WzJ314LH1NXq7Rf.dOYPP2uvzk08g.eAl9l4DRG4YsxavEV4W")) {
            System.out.println("encoder: true");
        }

        System.out.println("------------华丽的分割线-----------------------");
        String md5Password = Md5Util.encode("abel");
        System.out.println("Md5Password:  " + md5Password);
        System.out.println("encoder:  " + encoder.encode(md5Password));
        if (encoder.matches(md5Password, "$2a$10$37MXEfzlbtC6QSsRTlRhIOmykMRJtO5mU8Y.yiJBjy1x4WYWFR5gG")) {
            System.out.println("Md5Password: true");
        }

    }
}
