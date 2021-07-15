package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdatePatchReq;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.Conference_history;
import com.ssafy.db.entity.User_conference;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    ConferenceHistoryRepository conferenceHistoryRepository;

    @Autowired
    UserConferenceRepository userConferenceRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        user.setUserId(userRegisterInfo.getUserId());
        // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
        user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
        // 유저 이름
        user.setName(userRegisterInfo.getName());
        // 유저 deparment
        user.setDepartment(userRegisterInfo.getDepartment());
        // 유저 position
        user.setPosition(userRegisterInfo.getPosition());
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        User user = userRepositorySupport.findUserByUserId(userId).get();
        return user;
    }

    @Override
    public User updateUserByUserId(String userId, UserUpdatePatchReq userUpdateInfo) {
        User user = getUserByUserId(userId);
        user.setDepartment(userUpdateInfo.getDepartment());
        user.setPosition(userUpdateInfo.getPosition());
        user.setName(userUpdateInfo.getName());
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByUserId(String userId) {
        User user = getUserByUserId(userId);

//        List<User_conference> userConfList = (List<User_conference>) userConferenceRepository.findByUserId(user);
//        if (!userConfList.isEmpty()) {
//            for (User_conference conf : userConfList) {
//                userConferenceRepository.delete(conf);
//            }
//        }
//
//        List<Conference_history> historyList = (List<Conference_history>) conferenceHistoryRepository.findByUserId(user);
//        if (!historyList.isEmpty()) {
//            for (Conference_history history : historyList) {
//                conferenceHistoryRepository.delete(history);
//            }
//        }
//
//        List<Conference> conferences = (List<Conference>) conferenceRepository.findByUserId(user);
//        if(!conferences.isEmpty()){
//            for (Conference conference : conferences){
//                conferenceRepository.delete(conference);
//            }
//        }

        userRepository.delete(user);
    }


}
