package com.study.board.domain.board.dto.resp;

import com.study.board.domain.board.entity.Board;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetBoardRespDto(
    Long id,
    String title,
    String content,
    String author,
    LocalDateTime createdDate,
    LocalDateTime updatedDate
    // 결과로 보여줄 필드

) {


}
