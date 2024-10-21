package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.service.CreateBoardService;
import com.study.board.domain.board.service.GetBoardService;
import com.study.board.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Board", description = "게시판 API")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final CreateBoardService createBoardService;
    private final GetBoardService getBoardService;

    // 게시판 생성
    @Operation(summary = "게시판 생성", description = "게시판을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body("로그인 해주세요");
        }
        createBoardService.createBoard(req, user);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성이 완료되었습니다.");
    }

    // 게시판 상세 조회
    @Operation(summary = "게시판 상세 조회", description = "게시판의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {

        return ResponseEntity.ok().body(getBoardService.getBoard(id));
    }
    //게시판 목록 조회
    @Operation(summary = "게시판 목록 조회", description = "게시판의 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(){
        return ResponseEntity.ok(getBoardService.getBoardList());
    }

}
