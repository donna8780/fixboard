package com.study.board.domain.controller;

import com.study.board.domain.dto.req.CreateBoardReqDto;
import com.study.board.domain.service.CreateBoardService;
import com.study.board.domain.service.GetBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final CreateBoardService createBoardService;
    private final GetBoardService getBoardService;

    // 게시판 생성
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req) {
        createBoardService.createBoard(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성이 완료되었습니다.");
    }

    // 게시판 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok().body(getBoardService.getBoard(id));
    }

}
