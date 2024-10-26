package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {

    private final BoardRepository boardRepository;

    public GetBoardRespDto getBoard(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("ID가 존재하지 않습니다.")).of();
    }

    public List<GetBoardRespDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();

        return boardList.stream()
            .map(Board::of)
            .toList();

        /*
        boardList.stream()은 게시글 리스트를 Stream으로 변환합니다.
        Stream: Java의 스트림 API는 컬렉션의 데이터를 처리하는 데 유용.
        .map(Board::of)는 Board 객체를 GetBoardRespDto로 변환합니다.
        **Board::of**는 Board 클래스의 of() 메서드를 사용하여 Board 객체를 GetBoardRespDto로 변환.
         이 방식은 메서드 참조라 불림.
        .toList()는 변환된 스트림을 다시 리스트(List) 형태로 변환하여 반환.

        */
    }
}
