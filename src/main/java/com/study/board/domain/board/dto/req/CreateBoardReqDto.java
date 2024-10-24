package com.study.board.domain.board.dto.req;

import com.study.board.domain.board.entity.Board;

import java.time.LocalDateTime;

// java 15 이후 나온 클래스
public record CreateBoardReqDto(
        String title,
        String content,
        String author
) {
    public Board of() {// 클라이언트로부터 받아온 정보로 board 테이블에 행 추가 함을 위함.
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
