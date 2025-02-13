package com.ililog.api.service;


import com.ililog.api.crypto.PasswordEncoder;
import com.ililog.api.domain.User;
import com.ililog.api.exception.AlreadyExistsEmailException;
import com.ililog.api.exception.InvalidSigninInformation;
import com.ililog.api.repository.UserRepository;
import com.ililog.api.request.Login;
import com.ililog.api.request.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); // 모든 사용자 삭제 (초기화)
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll(); // 테스트 후 정리
    }

//    @Autowired
//    private PasswordEncoder encoder;

//    @Test
//    @DisplayName("로그인 성공")
//    void test3() {
//        // given
//
//        String ecnryptedPassword = encoder.encrypt("1234");
//
//        User user = User.builder()
//                .email("abcd2222@gmail.com")
//                .password(ecnryptedPassword)
//                .name("ili")
//                .build();
//        userRepository.save(user);
//
//        Login login = Login.builder()
//                .email("abcd2222@gmail.com")
//                .password("1234")
//                .build();
//
//        // when
//        Long userId = authService.signin(login);
//
//        // then
//        assertNotNull(userId);
//    }
//
//    @Test
//    @DisplayName("로그인시 비밀번호 틀림")
//    void test4() {
//        // given
//        String ecnryptedPassword = encoder.encrypt("1234");
//
//        User user = User.builder()
//                .email("abcd2222@gmail.com")
//                .password(ecnryptedPassword)
//                .name("ili")
//                .build();
//        userRepository.save(user);
//
//        Login login = Login.builder()
//                .email("abcd2222@gmail.com")
//                .password("5678")
//                .build();
//
//        // expected
//        assertThrows(InvalidSigninInformation.class,
//                () -> authService.signin(login));
//    }


    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        // given
        Signup signup = Signup.builder()
                .email("abcd2222@gmail.com")
                .password("1234")
                .name("ili")
                .build();

        // when
        authService.signup(signup);

        // then
        assertEquals(1, userRepository.count());

        User user = userRepository.findAll().iterator().next();
        assertEquals("abcd2222@gmail.com", user.getEmail());
        assertNotNull(user.getPassword());
        assertNotEquals("1234", user.getPassword());
        assertEquals("ili", user.getName());
    }

    @Test
    @DisplayName("회원가입시 중복된 이메일")
    void test2() {
        // given
        User user = User.builder()
                .email("abcd2222@gmail.com")
                .password("1234")
                .name("ili")
                .build();
        userRepository.save(user);

        Signup signup = Signup.builder()
                .email("abcd2222@gmail.com")
                .password("1234")
                .name("ili")
                .build();

        // expected
        assertThrows(AlreadyExistsEmailException.class, () -> authService.signup(signup));
    }

}