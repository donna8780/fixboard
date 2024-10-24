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
//        return boardMapper.getBoard(id).of();

        Optional<Board> optionalBoard = boardRepository.findById(id);
        // findById() : 반환형이 Optional<T>이다
        // Optional<T> : 값이 들어있을수도, 비어있을수도있음

        if (optionalBoard.isPresent()) {
            // optionalBoard에 값이 들어있는 경우
            return optionalBoard.get().of();
        } else {
            throw new NoSuchElementException("해당 ID와 일치하는 게시글이 존재하지 않습니다.");
            // NoSuchElementException : Optional에 값이 비어있는 empty 상태일 때 .get()을 하면 발생하는 예외
        }
    }

    public List<GetBoardRespDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();

        return boardList.stream()
            .map(Board::of)
            .toList();
    }
}
