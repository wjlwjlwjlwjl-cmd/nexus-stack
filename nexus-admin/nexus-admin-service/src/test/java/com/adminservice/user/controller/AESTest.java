package com.adminservice.user.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.adminservice.AdminServiceApplication;
import com.commoncore.utils.AESUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AdminServiceApplication.class)
public class AESTest {

    @Test
    void aesTest() {
        String phoneNumber = "18888888888";
        System.out.println(AESUtil.encryptHex(phoneNumber));
        String password = "123456789";
        System.out.println(AESUtil.encryptHex(password));
        System.out.println(DigestUtil.sha256Hex(password));
    }
}
