package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
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
      public void boardUpdate(Long id, UpdateBoardReqDto req){

        // 해당 ID의 게시글을 데이터베이스에서 찾음
        Optional<Board> optionalBoard = boardRepository.findById(id);

        // 게시글이 존재하는지 확인
        if (optionalBoard.isPresent()) { // 게시글 ID가 존재한다면
          Board board = optionalBoard.get(); //optional 객체에서 Board 객체를 가져옴
          board.updateBoard(req.title(), req.content());// 게시글의 제목과 내용을 UpdateBoardReqDto에서 받은 값으로 업데이트

          boardRepository.save(board);
          // save는 db에 해당 id의 값이 없으면 생성해주고,
          // 동일한 id가 있는 경우는 update 해준다

        } else {
          throw new NoSuchElementException(" ID로 찾으려는 게시글이 존재하지 않을.");
        }

    }

}
