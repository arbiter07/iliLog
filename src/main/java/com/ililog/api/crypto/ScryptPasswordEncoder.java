package com.ililog.api.crypto;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

// @Component
// Security로 변경함
public class ScryptPasswordEncoder implements PasswordEncoder {

    private static final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
            16,
            8,
            1,
            32,
            64);

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encrpytedPassword) {
        return encoder.matches(rawPassword, encrpytedPassword);
    }
}
