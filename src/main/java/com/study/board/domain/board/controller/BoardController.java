package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.service.CreateBoardService;
import com.study.board.domain.board.service.DeleteBoardService;
import com.study.board.domain.board.service.GetBoardService;
import com.study.board.domain.board.service.UpdateBoardService;
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
    private final UpdateBoardService updateBoardService;


    // 게시글 생성
    @Operation(summary = "게시판 생성", description = "게시판을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req) {
        // @RequestBody를 사용하면 본문에서 JSON 데이터를 읽어와서 지정된 DTO 객체(CreateBoardReqDto)로 자동 변환해줌
        createBoardService.createBoard(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성이 완료되었습니다.");
    }

    // 게시글 상세 조회
    @Operation(summary = "게시판 상세 조회", description = "게시판의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {

        return ResponseEntity.ok().body(getBoardService.getBoard(id));
    }

    //게시글 목록 조회
    @Operation(summary = "게시판 목록 조회", description = "게시판의 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList() {
        return ResponseEntity.ok(getBoardService.getBoardList());
    }

    //게시글 삭제
    @Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다.")
    @DeleteMapping("/delete/{id}")//board의 아이디
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
            deleteBoardService.deleteBoard(id);
            return ResponseEntity.ok("게시판이 삭제되었습니다.");

    }


    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PutMapping("/update/{id}") // board의 아이디를 입력받음
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardReqDto req) {
            updateBoardService.boardUpdate(id, req); // 게시글 수정 호출
            return ResponseEntity.ok("게시글을 수정하였습니다.");
        }


/*    @Operation(summary = "페이지네이션", description = "게시글을 목록")
    @GetMapping("/page")
    public ResponseEntity<?> getBoardListPagination(
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize) {
        return ResponseEntity.ok()
            .body(getBoardListPaginationService.getBoardListPagination(pageNumber, pageSize));
    }*/
}

