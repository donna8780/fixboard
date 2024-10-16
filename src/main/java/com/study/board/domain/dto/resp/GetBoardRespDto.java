package com.study.board.domain.dto.resp;

import lombok.Builder;

@Builder
public record GetBoardRespDto(
        String title,
        String content,
        String author
) {
}
