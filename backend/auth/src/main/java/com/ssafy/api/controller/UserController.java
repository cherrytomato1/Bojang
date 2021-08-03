package com.ssafy.api.controller;


import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.response.UserInfoGetResponse;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.AuthUserRepository;
import com.ssafy.security.CurrentUser;
import com.ssafy.security.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @ApiOperation(value = "AuthUser", notes = "카카오 API를 이용한 로그인", response = AuthUser.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    public AuthUser getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        System.out.println("[get] ================> profile");
        return authUserRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }


}
