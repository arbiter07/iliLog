package com.ililog.api.service;

import com.ililog.api.domain.User;
import com.ililog.api.exception.UserNotFound;
import com.ililog.api.repository.UserRepository;
import com.ililog.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        return new UserResponse(user);
    }
}
