package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdatePatchRequest;
import com.ssafy.db.entity.User;
import com.ssafy.security.UserPrincipal;

public interface UserService {

	String getUserIdByUserPrincipal(UserPrincipal userPrincipal);

	User getUser(String id);

	User updateUserType(String userId, Long userTypeId);

	User updateUser(UserUpdatePatchRequest userUpdatePatchRequest, String userId);

	void deleteUser(String userId);

}
