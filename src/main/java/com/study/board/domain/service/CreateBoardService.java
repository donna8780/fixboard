package com.study.board.domain.service;

import com.study.board.domain.dto.req.CreateBoardReqDto;
import com.study.board.global.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 디폴트 값이 서비스 로직이 정상적으로 완료되지않으면 디비에 적용이 되지않습니다.
@Transactional
@Service
@RequiredArgsConstructor
public class CreateBoardService {
    private final BoardMapper boardMapper;

    // 게시판 등록
    public void createBoard(CreateBoardReqDto req) {
        boardMapper.createBoard(req.of());
    }
}
