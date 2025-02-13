package com.ililog.api.crypto;

// @Component
public class TestPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String rawPassword) {
        return rawPassword;
    }

    @Override
    public boolean matches(String rawPassword, String encrpytedPassword) {
        return rawPassword.equals(encrpytedPassword);
    }
}
