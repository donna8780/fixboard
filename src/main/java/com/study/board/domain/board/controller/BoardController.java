package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.service.CreateBoardService;
import com.study.board.domain.board.service.DeleteBoardService;
import com.study.board.domain.board.service.GetBoardService;
import com.study.board.domain.board.service.UpdateBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


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
    public ResponseEntity<CreateBoardReqDto> createBoard(@RequestBody CreateBoardReqDto req) {

       try{CreateBoardReqDto response  = createBoardService.createBoard(req);
           return ResponseEntity.status(HttpStatus.CREATED).body(response);
       } catch (IllegalArgumentException e) {
           // 잘못된 요청일 때
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."); // 상태 코드 400
       } catch (Exception e) {
           // 기타 예외 처리
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "게시판 생성 중 오류가 발생했습니다."); // 상태 코드 500
       }

    }

    // 게시글 상세 조회
    @Operation(summary = "게시판 상세 조회", description = "게시판의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<GetBoardRespDto> getBoard(@PathVariable Long id) {
        try {
            GetBoardRespDto response = getBoardService.getBoard(id);
            return ResponseEntity.ok(response); // 정상 조회 시 상태 코드 200
        }
        catch (EntityNotFoundException e) {
            // 게시글을 찾을 수 없는 경우
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."); // 상태 코드 404
        } catch (Exception e) {
            // 기타 예외 처리
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "게시글 조회 중 오류가 발생했습니다."); // 상태 코드 500
        }
    }



    //게시글 목록 조회
    @Operation(summary = "게시판 목록 조회", description = "게시판의 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<GetBoardRespDto>> getBoardList() {

        try{
            List<GetBoardRespDto> listResponse = getBoardService.getBoardList();
            return ResponseEntity.ok(listResponse); // 정상 조회 시 상태 코드 200과 함께 목록 반환
        }
        catch (EntityNotFoundException e) {
            // 데이터가 존재하지 않을 때
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 목록을 찾을 수 없습니다.");
        } catch (Exception e) {
            // 기타 예외 처리
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "게시글 목록 조회 중 오류가 발생했습니다.");
        }
    }

    //게시글 삭제
    @Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다.")
    @DeleteMapping("/delete/{id}")//board의 아이디
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        try {
            deleteBoardService.deleteBoard(id);
            return ResponseEntity.ok("게시판이 삭제되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("게시판을 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("게시판 삭제 중 오류가 발생했습니다.");
        }
    }


    //게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PutMapping("/update/{id}") // board의 아이디를 입력받음
    public ResponseEntity<UpdateBoardReqDto> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardReqDto req) {
        try {
            updateBoardService.boardUpdate(id, req); // 게시글 수정 호출

            UpdateBoardReqDto response = new UpdateBoardReqDto(req.title(), req.content());
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            // 게시글을 찾을 수 없는 경우
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        } catch (Exception e) {
            // 수정 중 예기치 않은 오류 발생
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "게시글 수정 중 오류가 발생했습니다.");
        }

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

