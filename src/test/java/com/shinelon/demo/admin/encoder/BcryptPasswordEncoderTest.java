package com.shinelon.demo.admin.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shinelon.demo.admin.utils.Md5Util;

/***
 *
 * @author syq
 *
 */
public class BcryptPasswordEncoderTest {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoder:  " + encoder.encode("abel"));
        System.out.println("encoder:  " + encoder.encode("abel").length());
        System.out.println("encoder:  " + encoder.encode("admin"));

        System.out.println("------------华丽的分割线-----------------------");
        String md5Password = Md5Util.encode("abel");
        System.out.println("Md5Password:  " + md5Password);
        System.out.println("encoder:  " + encoder.encode(md5Password));

    }
}
