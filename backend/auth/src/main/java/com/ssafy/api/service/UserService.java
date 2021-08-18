package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdatePatchRequest;
import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.entity.BankType;
import com.ssafy.db.entity.User;
import com.ssafy.security.UserPrincipal;

import java.util.List;

public interface UserService {

	String getUserIdByUserPrincipal(UserPrincipal userPrincipal);

	User getUser(String id);

	AuthUser getAuthUser(UserPrincipal userPrincipal);

	User updateUserType(String userId, Long userTypeId);

	User updateUser(UserUpdatePatchRequest userUpdatePatchRequest, String userId);

	void deleteUser(UserPrincipal userPrincipal);

	List<BankType> getBankType();
}
