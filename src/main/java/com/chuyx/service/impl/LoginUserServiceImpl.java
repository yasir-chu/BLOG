package com.chuyx.service.impl;

import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.UserMapper;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author cyx
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<GrantedAuthority> getRoles(String role) {
        if (role.equals("ordinary")) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_Ordinary");
        } else if (role.equals("author")) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_Author");
        } else {
            return role.equals("admin") ? AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_Admin") : AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_Ordinary");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserDTO userInfo = this.userMapper.queryUserByUsername(username);
        List<GrantedAuthority> roles = new ArrayList();
        if (NormalConstant.ZERO.equals(userInfo.getCapacity())) {
            roles = getRoles("ordinary");
        } else if (NormalConstant.ONE.equals(userInfo.getCapacity())) {
            roles = getRoles("author");
        } else if (NormalConstant.TWE.equals(userInfo.getCapacity())) {
            roles = getRoles("admin");
        }
       return new User(userInfo.getUname(), userInfo.getPassword(), (Collection) roles);
    }
}
