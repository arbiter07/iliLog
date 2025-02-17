package com.ililog.api.service;

import com.ililog.api.domain.Post;
import com.ililog.api.domain.User;
import com.ililog.api.exception.PostNotFound;
import com.ililog.api.repository.PostRepository;
import com.ililog.api.repository.UserRepository;
import com.ililog.api.request.PostCreate;
import com.ililog.api.request.PostEdit;
import com.ililog.api.request.PostSearch;
import com.ililog.api.response.PagingResponse;
import com.ililog.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // given
        var user = User.builder()
                .name("name1234")
                .email("abcd22@gmail.com")
                .password("1234")
                .build();
        userRepository.save(user);
        // when
        postService.write(user.getId(),postCreate);

        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        // given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        // when
        PostResponse response = postService.get(requestPost.getId());

        // then
        assertNotNull(response);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", response.getTitle());
        assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3() {
        // given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("foo" + i)
                        .content("bar1" + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        // when
        PagingResponse<PostResponse> postList = postService.getList(postSearch);

        // then
        assertEquals(20L, postList.getTotalCount());
        assertEquals("foo19", postList.getItems().get(0).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        // given
        Post post = Post.builder()
                .title("제목Title")
                .content("내용Content")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목Title")
                .content("내용Content")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
        assertEquals("제목Title", changedPost.getTitle());
    }

    @Test
    @DisplayName("글 내용 수정")
    void test5() {
        // given
        Post post = Post.builder()
                .title("제목Title")
                .content("내용Content")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목Title")
                .content("초가집")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
        assertEquals("초가집", changedPost.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        // given
        Post post = Post.builder()
                .title("제목Title")
                .content("내용Content")
                .build();
        postRepository.save(post);

        // when
        postService.delete(post.getId());

        // then
        assertEquals(0, postRepository.count());
    }

    @Test
    @DisplayName("글 1개 조회 - 존재하지 않는 글")
    void test7() {
        // given
        Post post = Post.builder()
                .title("제목Title")
                .content("내용Content")
                .build();
        postRepository.save(post);

        // expected
        assertThrows(PostNotFound.class, () -> {
            postService.get(post.getId() + 1L);
        });
    }

    @Test
    @DisplayName("게시글 삭제 - 존재하지 않는 글")
    void test8() {
        // given
        Post post = Post.builder()
                .title("제목Title")
                .content("내용Content")
                .build();
        postRepository.save(post);

        // expected
        assertThrows(PostNotFound.class, () -> {
            postService.delete(post.getId() + 1L);
        });
    }

    @Test
    @DisplayName("글 내용 수정 - 존재하지 않는 글")
    void test9() {
        // given
        Post post = Post.builder()
                .title("제목Title")
                .content("내용Content")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목Title")
                .content("초가집")
                .build();

        // expected
        assertThrows(PostNotFound.class, () -> {
            postService.edit(post.getId() + 1L, postEdit);
        });
    }
}









