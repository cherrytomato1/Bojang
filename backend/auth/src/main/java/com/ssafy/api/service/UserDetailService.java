package com.ssafy.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
    UserDetails loadUserByUsername(String id) throws UsernameNotFoundException;

    UserDetails loadUserById(String id);
}
