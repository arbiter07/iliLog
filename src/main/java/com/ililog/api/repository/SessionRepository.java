package com.ililog.api.repository;

import com.ililog.api.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Security 도입으로 인한 삭제
public interface SessionRepository { // extends CrudRepository<Session, Long> {

    // Optional<Session> findByAccessToken(String accessToken);
}
