package com.ililog.api.service;

import com.ililog.api.domain.User;
import com.ililog.api.exception.AlreadyExistsEmailException;
import com.ililog.api.repository.UserRepository;
import com.ililog.api.request.Signup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    // Security Login 방식 사용에 따른 중지
//    @Transactional
//    public Long signin(Login login) {
//        User user = userRepository.findByEmail(login.getEmail())
//                .orElseThrow(InvalidSigninInformation::new);
//        var matches = encoder.matches(login.getPassword(), user.getPassword());
//        if (!matches) {
//            log.info("!matches" );
//            throw new InvalidSigninInformation();
//        }
//
//        return user.getId();
//    }

    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findByEmail(signup.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }
        String encryptedPassword = encoder.encode(signup.getPassword());

        var user = User.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .name(signup.getName())
                .build();
        userRepository.save(user);
    }
}
