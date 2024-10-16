package com.study.board.domain.entity;

import com.study.board.domain.dto.resp.GetBoardRespDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private String author;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public GetBoardRespDto of() {
        return GetBoardRespDto.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
