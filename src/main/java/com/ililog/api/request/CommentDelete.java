package com.ililog.api.request;

import lombok.Getter;

@Getter
public class CommentDelete {

    private String password;

    public CommentDelete() {

    }

    public CommentDelete(String password) {
        this.password = password;
    }
}
