package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import com.study.board.domain.user.entity.User;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UpdateBoardService {
    private final BoardRepository boardRepository;

    //게시글 수정
    //게시글의 고유번호인 id와 dto를 매개변수로 받아서
      public void boardUpdate(Long id, UpdateBoardReqDto req, User user){
// 해당 ID의 게시글을 데이터베이스에서 찾음
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("ID로 찾으려는 게시글이 존재하지 않습니다."));

//수정된 내용을 Board 객체에 적용
        board.update(req.title(), req.content()); // 새로운 메서드 호출로 수정
    }

}
