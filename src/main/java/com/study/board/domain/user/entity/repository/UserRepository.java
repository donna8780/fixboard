package com.study.board.domain.user.entity.repository;

import com.study.board.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  //username(=아이디)로 유저를 찾는 메서드
  User findByUsername(String username);
}
