package com.study.board.domain.board.entity;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity//JPA엔티티임을 나타냄
@NoArgsConstructor//둘다 꼭 써줘야 빌터랑 엔티티가 적용됨
@AllArgsConstructor//둘다
@Table(name = "boards")

public class Board extends BaseTimeEntity {
    @Id //테이블의 기본키를 지정한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//db에 엔티티 추가 시 자동으로 값이 증가, 기본 키 값이 자동으로 생성
    private Long id;//인트 안댐 절대

    @Column(name="title")
    @Comment("게시글 제목")
    private String title;

    @Column(name="content")
    @Comment("게시글 내용")
    private String content;

    @Column(name = "author")
    @Comment("작성자")
    private String author;

    // update 메서드 추가
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    //연관관계 매핑 유저아이디 기준으로 조인을 해서 가져온다 다 대 일 관계
    //유저가 일이고 게시글이 다
    //@ManyToOne
   // @JoinColumn(name="user_id")

/*
    public GetBoardRespDto of(String name) {
        return GetBoardRespDto.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }*/
public GetBoardRespDto of() {
    // entity -> Dto로 변환
    return GetBoardRespDto.builder()
        .id(this.id)
        .title(this.title)
        .content(this.content)
        .author(this.author)
        .createdDate(this.getCreatedDate())
        .updatedDate(this.getUpdatedDate())
        .build();
}

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
