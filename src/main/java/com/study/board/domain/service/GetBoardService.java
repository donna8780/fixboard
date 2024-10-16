package com.study.board.domain.service;

import com.study.board.domain.dto.resp.GetBoardRespDto;
import com.study.board.domain.entity.Board;
import com.study.board.global.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {
    private final BoardMapper boardMapper;

    public GetBoardRespDto getBoard(Long id) {
        return boardMapper.getBoard(id).of();
    }
}
