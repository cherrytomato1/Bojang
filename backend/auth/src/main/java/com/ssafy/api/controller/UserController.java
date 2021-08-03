package com.ssafy.api.controller;


import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.repository.AuthUserRepository;
import com.ssafy.security.CurrentUser;
import com.ssafy.security.UserPrincipal;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "회원정보 CRUD API", tags = {"Auth."})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AuthUserRepository authUserRepository;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public AuthUser getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        System.out.println("[get] ================> profile");
        return authUserRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
