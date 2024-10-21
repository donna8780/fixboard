package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.service.CreateBoardService;
import com.study.board.domain.board.service.DeleteBoardService;
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
    private final DeleteBoardService deleteBoardService;

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
    public ResponseEntity<?> getBoardList() {
        return ResponseEntity.ok(getBoardService.getBoardList());
    }

    //게시판 삭제
    //1. 로그인 확인 2.로그인이 되어 있으면 로그인 한 사람과 글 쓴 사람이 일치 하는 지 확인
    //같으면 수정 가능 같지 않으면 수정 불가능
    //로그인이 안 되어 있으면, 일단 로그인 해
    @Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다.")
    @DeleteMapping("/delete/{id}")//board의 아이디
    public ResponseEntity<?> deleteBoard(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("로그인을 해주세요");
        }
        //로그인 한 사람과 글 쓴 사람이 일치하는지 확인
        Long loginId = user.getId();//로그인 한 사람
        Long boardAuthorID = deleteBoardService.comfirmId(id);//글 쓴 사람

        if (loginId.equals(boardAuthorID)) {
            deleteBoardService.deleteBoard(id);
            return ResponseEntity.ok("게시판이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
        }


    }
}
