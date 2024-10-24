package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional //서비스 로직에서 필수적임
@Service
@RequiredArgsConstructor
public class CreateBoardService {

    private final BoardRepository boardRepository;
    // 게시판 등록
    public void createBoard( CreateBoardReqDto req) {
        boardRepository.save(req.of());
    }//레파지토리해서 세이브하면 세이브댐
}
