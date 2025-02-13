package com.ililog.api.crypto;

public interface PasswordEncoder {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encrpytedPassword);
}
