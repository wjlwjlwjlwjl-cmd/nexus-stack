package com.portalservice.user.service;

import com.adminapi.appuser.domain.dto.AppUserDTO;
import com.adminapi.appuser.domain.dto.UserEditReqDTO;
import com.commonsecurity.domain.dto.TokenDTO;
import com.portalservice.user.entity.dto.LoginDTO;
import com.portalservice.user.entity.dto.UserDTO;

/**
 * 门户用户服务接口
 */
public interface IUserService {

    TokenDTO login(LoginDTO loginDTO);

    String sendCode(String phone);

    void edit(UserEditReqDTO userEditReqDTO);

    UserDTO getLoginUser();

    void logout();
}
