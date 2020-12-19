package com.chuyx.service;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginUserService extends UserDetailsService {
   List<GrantedAuthority> getRoles(String role);
}
