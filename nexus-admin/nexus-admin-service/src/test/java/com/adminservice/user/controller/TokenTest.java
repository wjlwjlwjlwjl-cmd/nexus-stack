package com.adminservice.user.controller;

import com.adminservice.AdminServiceApplication;
import com.commonsecurity.domain.dto.LoginUserDTO;
import com.commonsecurity.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AdminServiceApplication.class)
public class TokenTest {
    @Autowired
    private TokenService tokenService;

    @Test
    void tokenTest() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setUserId(100L);
        loginUserDTO.setUserName("zhangSan");
        loginUserDTO.setUserFrom("sys");
        tokenService.createToken(loginUserDTO);
    }
}
