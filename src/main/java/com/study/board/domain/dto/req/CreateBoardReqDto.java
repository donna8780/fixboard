package com.study.board.domain.dto.req;

import com.study.board.domain.entity.Board;

import java.time.LocalDateTime;

// java 15 이후 나온 클래스
public record CreateBoardReqDto(
        String title,
        String content,
        String author
) {
    public Board of() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
