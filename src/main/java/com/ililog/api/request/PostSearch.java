package com.ililog.api.request;

import lombok.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 생성자 추가 (빌더용)
@Builder
@ToString
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public long getOffset() {
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }
}
