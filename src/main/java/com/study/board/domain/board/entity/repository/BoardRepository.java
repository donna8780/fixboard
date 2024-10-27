package com.study.board.domain.board.entity.repository;

import com.study.board.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
  /*Board: JpaRepository가 관리 할 엔티티 클래스를 의미, Board엔티티가 데이터 베이스의
  테이블과 매핑되며, JpaRepository는 이 엔티티와 관련된 CRUD작업을 처리
  Long: Board엔티티의 기본키(id)의 타입을 정의, Board엔티티를 조회하거나 삭제할 때
  기본키로 Long타입의 값을 기대하게 됨
*/
  Page<Board> findAllByOrderByCreatedDateDesc(Pageable pageable);
  /*작성된 날짜(CreatedDate) 기준으로 게시글 목록을 내림차순으로 정렬하여 페이징 처리된 결과를 반환*/
}
