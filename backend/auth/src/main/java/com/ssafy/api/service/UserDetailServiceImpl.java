package com.ssafy.api.service;

import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.repository.AuthUserRepository;
import com.ssafy.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    private final AuthUserRepository authUserRepository;

    public UserDetails loadUserByUsername(String id)
            throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with Username => id : " + id)
                );

        return UserPrincipal.create(authUser);
    }

    public UserDetails loadUserById(String id) {
        AuthUser authUser = authUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(authUser);
    }
}
