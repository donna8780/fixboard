package com.study.board.global.mapper.board;

import com.study.board.domain.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 생성
    void createBoard(Board board);
    // 게시글 목록 조회
    List<Board> getBoardList();
    // 게시글 상세 조회
    Board getBoard(Long id);
    // 게시글 삭제
    void deleteBoard(Long id);
    //유저 아이디와 글쓴 사람 일치하는지 확인하는 메서드
    Long confirmId(Long userid);
    // 게시글 수정
    void updateBoard(Board board);


}
