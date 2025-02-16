package com.ililog.api.repository;

import com.ililog.api.domain.Post;
import com.ililog.api.request.PostSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostRepositoryCustom {

    Page<Post> getList(PostSearch postSearch);
}
