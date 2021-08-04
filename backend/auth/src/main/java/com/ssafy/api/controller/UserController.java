package com.ssafy.api.controller;


import com.ssafy.api.request.UserUpdatePatchRequest;
import com.ssafy.api.response.UserIdGetResponse;
import com.ssafy.api.response.UserResponse;
import com.ssafy.api.service.UserService;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.AuthUserRepository;
import com.ssafy.security.CurrentUser;
import com.ssafy.security.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "회원정보 CRUD API", tags = {"Auth-User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

	private final AuthUserRepository authUserRepository;
	private final UserService userService;

	@GetMapping("/profile")
	@PreAuthorize("hasRole('USER')")
	@ApiIgnore
	@ApiOperation(value = "AuthUser 객체 반환", notes = "토큰에 담긴 AuthUser 객체 반환", response =
		                                                                         AuthUser.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public AuthUser getAuthUser(@ApiIgnore @CurrentUser UserPrincipal userPrincipal) {
		AuthUser authUser = authUserRepository.findById(userPrincipal.getId())
			                    .orElseThrow(() -> new ResourceNotFoundException("AuthUser", "id",
				                    userPrincipal.getId()));
		log.debug("get Auth User : " + authUser);
		return authUser;
	}

	@GetMapping("/info")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "User 정보 반환", notes = "토큰 정보에 담긴 유저 반환", response = UserResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<UserResponse> getUser(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal) {
		User user;
		try {
			user = userService.getUser(userPrincipal.getUser().getId());
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404).body(UserResponse.of(404, "유저 정보 조회 실패", null));
		}
		return ResponseEntity.status(200).body(UserResponse.of(200, "Success", user));
	}

	@GetMapping("/userid")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "User ID 반환", notes = "토큰 정보에 담긴 유저 ID 반환", response =
		                                                                  UserIdGetResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<UserIdGetResponse> getUserId(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal) {
		Optional<String> userId = Optional.ofNullable(userPrincipal.getUser().getId());
		return userId.map(
			id -> ResponseEntity.status(200).body(UserIdGetResponse.of(200, "Success", id)))
			       .orElseGet(() -> ResponseEntity.status(404)
				                        .body(UserIdGetResponse.of(404, "유저 정보 조회 실패", null)));

	}

//	@PatchMapping("/update")
//	@PreAuthorize("hasRole('USER')")
//	@ApiOperation(value = "User ID 반환", notes = "토큰 정보에 담긴 유저 ID 반환", response =
//		                                                                  UserIdGetResponse.class)
//	@ApiResponses(value = {
//		@ApiResponse(code = 200, message = "OK"),
//		@ApiResponse(code = 400, message = "Bad Request"),
//		@ApiResponse(code = 401, message = "Unauthorized"),
//		@ApiResponse(code = 403, message = "Forbidden"),
//		@ApiResponse(code = 404, message = "Not Found")
//	})
//	public ResponseEntity<UserResponse> updateUser(
//		@ApiIgnore @CurrentUser UserPrincipal userPrincipal,
//		UserUpdatePatchRequest userUpdatePatchRequest) {
//
//	}
////
//    public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보",
//    required = true) UserLoginPostReq loginInfo) {
//        String userId = loginInfo.getId();
//        String password = loginInfo.getPassword();
//
//        User user = userService.getUserByUserId(userId);
//
//        //해당하는 아이디를 찾지 못했을 때 실패 응답
//        if(user == null){
//            return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "Invalid ID", null));
//        }
//
//        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
//        if(!passwordEncoder.matches(password, user.getPassword())) {
//            // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
//            return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password",
//            null));
//        }
//
//        // 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
//        return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken
//        (userId)));
//    }
}
