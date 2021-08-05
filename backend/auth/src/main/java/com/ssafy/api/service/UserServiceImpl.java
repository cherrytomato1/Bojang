package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdatePatchRequest;
import com.ssafy.common.exception.handler.BadRequestException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.UserTypeEnum;
import com.ssafy.db.entity.BankType;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.UserType;
import com.ssafy.db.repository.AuthUserRepository;
import com.ssafy.db.repository.BankTypeRepository;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserTypeRepository;
import com.ssafy.security.UserPrincipal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final AuthUserRepository authUserRepository;

	private final UserRepository userRepository;

	private final UserTypeRepository userTypeRepository;

	private final BankTypeRepository bankTypeRepository;

	/*
		UserPrincipal에서 userId 반환
		없을 시 ResourceNotFoundException 반환
	 */
	@Override
	public String getUserIdByUserPrincipal(UserPrincipal userPrincipal) {
		Optional<String> userId = Optional.ofNullable(userPrincipal.getUser().getId());
		return userId.orElseThrow(() -> new ResourceNotFoundException("UserPrincipal", "userId",
			userPrincipal.getUser().getId()));
	}

	@Override
	public User getUser(String id) {
		return findUserById(id);
	}

	@Override
	public User updateUserType(String userId, Long userTypeId) {
		User existingUser = findUserById(userId);
		try {
			updateUserType(existingUser, userTypeId);
		} catch (ResourceNotFoundException ex) {
			throw new BadRequestException(ex.getMessage());
		}
		return userRepository.save(existingUser);
	}

	@Override
	public User updateUser(UserUpdatePatchRequest userUpdatePatchRequest, String userId) {
		User existingUser = findUserById(userId);

		validateUpdateUserRequest(userUpdatePatchRequest);

		try {
			existingUser.setName(userUpdatePatchRequest.getName());
			updateUserType(existingUser, userUpdatePatchRequest.getUserType());
			existingUser.setPhoneNumber(userUpdatePatchRequest.getPhoneNumber());

			if (existingUser.getUserType().getName()
				    .equalsIgnoreCase(UserTypeEnum.SELLER.toString())) {
				updateBankType(existingUser, userUpdatePatchRequest.getBankType());
				existingUser.setAccountNumber(userUpdatePatchRequest.getAccountNumber());
			}
		} catch (ResourceNotFoundException ex) {
			throw new BadRequestException(ex.getMessage());
		}

		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(UserPrincipal userPrincipal) {

		try {
			String userId = getUserIdByUserPrincipal(userPrincipal);
			String authId = userPrincipal.getId();

			authUserRepository.deleteById(authId);
			userRepository.deleteById(userId);
		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
	}

	/*
		id에서 유저 객체 반환
    */
	private User findUserById(String userId) {
		return userRepository.findById(userId)
			       .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	/*
		userUpdatePatchRequest가 유효한지 확인
		null인 값을 확인하면 BadRequestException 발생
	 */
	private void validateUpdateUserRequest(UserUpdatePatchRequest userUpdatePatchRequest) {
		if (userUpdatePatchRequest.getName() == null || userUpdatePatchRequest.getUserType() == null
			    || userUpdatePatchRequest.getPhoneNumber() == null) {
			throw new BadRequestException(
				"User Update Request is invalidate : " + userUpdatePatchRequest.getName() + " "
					+ userUpdatePatchRequest.getUserType() + " " + userUpdatePatchRequest
						                                               .getPhoneNumber());
		}
	}

	private void updateUserType(User user, Long userTypeId) {
		Optional<UserType> userType = userTypeRepository
			                              .findById(userTypeId);
		user.setUserType(userType.orElseThrow(
			() -> new ResourceNotFoundException("UserType", "Id",
				userType)));
	}

	private void updateBankType(User user, Long bankTypeId) {
		Optional<BankType> bankType = bankTypeRepository
			                              .findById(bankTypeId);
		user.setBankType(bankType.orElseThrow(
			() -> new ResourceNotFoundException("BankType", "Id",
				bankTypeId)));
	}
}
