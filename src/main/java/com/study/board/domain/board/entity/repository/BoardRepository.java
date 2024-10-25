package com.study.board.domain.board.entity.repository;

import com.study.board.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  //Optional<User> findByUsername(String username);
}
