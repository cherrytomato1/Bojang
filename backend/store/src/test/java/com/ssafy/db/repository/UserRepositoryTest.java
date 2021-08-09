//package com.ssafy.db.repository;
//
//import com.ssafy.db.entity.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class UserRepositoryTest {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void createUser() {
//
//        User user = new User();
//        user.setName("권도엽");
//        user.setRegisterTime(LocalDateTime.now());
//        user.setZipCode(1);
//        user.setAccountNumber("1234");
//        user.setAddress("싸피");
//        user.setAddressDetail("서울 5반 8팀");
//
//        userRepository.save(user);
//
//    }
//
//    @Test
//    public void findById() {
//
//        User user = new User();
//        user.setName("권도엽");
//        user.setRegisterTime(LocalDateTime.now());
//        user.setZipCode(1);
//        user.setAccountNumber("1234");
//        user.setAddress("싸피");
//        user.setAddressDetail("서울 5반 8팀");
//        userRepository.save(user);
//        User user2 = userRepository.findById(user.getId()).get();
//        System.out.println(user.getId());
//        System.out.println(user2.getId());
//    }
//
//    @Test
//    public void findId(){
//        User user = userRepository.findById("S99b735248a08").get();
//        System.out.println(user.getId());
//    }
//
//    @Test
//    public void findByName(){
//        List<User> list = userRepository.findByName("권도엽");
//
//        for(User user : list){
//            System.out.println(user.getId());
//        }
//    }
//}