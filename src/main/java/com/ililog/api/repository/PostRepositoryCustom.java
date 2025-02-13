package com.ililog.api.repository;

import com.ililog.api.domain.Post;
import com.ililog.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
