package com.ssafy.api.controller;


import com.ssafy.api.request.UserUpdatePatchRequest;
import com.ssafy.api.response.AuthUserProfileGetResponse;
import com.ssafy.api.response.UserDeleteResponse;
import com.ssafy.api.response.UserIdGetResponse;
import com.ssafy.api.response.UserGetResponse;
import com.ssafy.api.response.UserIdVaidateResponse;
import com.ssafy.api.response.UserUpdatePatchResponse;
import com.ssafy.api.service.UserService;
import com.ssafy.common.exception.handler.BadRequestException;
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
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "회원정보 CRUD API", tags = {"Auth-User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	private final UserService userService;

	@GetMapping("/profile")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "OAuth 프로필 반환", notes = "토큰에 담긴 AuthUser 객체 반환", response =
		                                                                       AuthUserProfileGetResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<AuthUserProfileGetResponse> getAuthUser(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal) {

		AuthUser authUser;
		try {
			authUser = userService.getAuthUser(userPrincipal);
			authUser.setUser(null);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404)
				       .body(AuthUserProfileGetResponse.of(404, "유저 정보 조회 실패", null));
		}
		log.debug("get Auth User : " + authUser);
		return ResponseEntity.status(200)
			       .body(AuthUserProfileGetResponse.of(200, "Success", authUser));
	}

	@GetMapping("/")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "User 정보 반환", notes = "토큰 정보에 담긴 유저 반환", response =
		                                                               UserGetResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<UserGetResponse> getUser(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal) {

		User user;
		try {
			user = userService.getUser(userPrincipal.getUser().getId());
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404).body(UserGetResponse.of(404, "유저 정보 조회 실패", null));
		}
		return ResponseEntity.status(200).body(UserGetResponse.of(200, "Success", user));
	}

	@GetMapping("/id")
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

		String userId;
		try {
			userId = userService.getUserIdByUserPrincipal(userPrincipal);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404)
				       .body(UserIdGetResponse.of(404, "유저 정보 조회 실패", null));
		}
		return ResponseEntity.status(200).body(UserIdGetResponse.of(200, "Success", userId));
	}

	@PostMapping("/validate-id")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "User ID 토큰 일치 여부 확인", notes = "토큰 정보에 담긴 유저 ID 반환", response =
		                                                                           UserIdVaidateResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<? super UserIdVaidateResponse> getUserId(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal,
		@ApiParam(value = "일치 여부를 확인할 유저 ID") @RequestBody String userId) {

		String tokenUserId;
		try {
			tokenUserId = userService.getUserIdByUserPrincipal(userPrincipal);
			if (!tokenUserId.equals(userId)) {
				return ResponseEntity.status(401)
					       .body(UserIdVaidateResponse.of(401, "토큰 정보와 userID가 일치하지 않습니다"));
			}
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404)
				       .body(UserIdVaidateResponse.of(404, "유저 정보 조회 실패"));
		}
		return ResponseEntity.status(200).body(UserIdVaidateResponse.of(200, "Success"));
	}


	@PatchMapping("/type")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "UserType 정보 Update", notes = "유저 Type 업데이트", response =
		                                                                    UserUpdatePatchResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<? super UserUpdatePatchResponse> updateUserType(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal,
		@ApiParam(value = "userType ID", example = "1") @RequestBody Long userTypeId) {

		try {
			String userId = userService.getUserIdByUserPrincipal(userPrincipal);
			userService.updateUserType(userId, userTypeId);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404)
				       .body(UserUpdatePatchResponse.of(404, "유저 정보 조회 실패"));
		} catch (BadRequestException ex) {
			return ResponseEntity.status(400)
				       .body(UserUpdatePatchResponse.of(400, ex.getMessage()));
		}

		return ResponseEntity.status(200)
			       .body(UserUpdatePatchResponse.of(200, "Success"));
	}

	@PatchMapping("/")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "User 정보 Update", notes = "모든 유저정보를 업데이트, Request Body에 모든 정보 필요",
		response =
			UserUpdatePatchResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<? super UserUpdatePatchResponse> updateUser(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal,
		@ApiParam(value = "업데이트할 유저 정보", required = true) @RequestBody UserUpdatePatchRequest userUpdatePatchRequest) {

		try {
			String userId = userService.getUserIdByUserPrincipal(userPrincipal);
			userService.updateUser(userUpdatePatchRequest, userId);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404)
				       .body(UserUpdatePatchResponse.of(404, "유저 정보 조회 실패"));
		} catch (BadRequestException ex) {
			return ResponseEntity.status(400)
				       .body(UserUpdatePatchResponse.of(400, ex.getMessage()));
		}

		return ResponseEntity.status(200)
			       .body(UserUpdatePatchResponse.of(200, "Success"));
	}

	@DeleteMapping("/")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "User delete", notes = "토큰에 저장된 유저의 정보를 삭제/탈퇴한다. ", response =
		                                                                          UserDeleteResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity<? super UserDeleteResponse> deleteUser(
		@ApiIgnore @CurrentUser UserPrincipal userPrincipal) {

		try {
			userService.deleteUser(userPrincipal);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404)
				       .body(UserUpdatePatchResponse.of(404, "유저 정보 조회 실패"));
		} catch (BadRequestException ex) {
			return ResponseEntity.status(400)
				       .body(UserUpdatePatchResponse.of(400, ex.getMessage()));
		}
		return ResponseEntity.status(200)
			       .body(UserUpdatePatchResponse.of(200, "Success"));
	}

}
