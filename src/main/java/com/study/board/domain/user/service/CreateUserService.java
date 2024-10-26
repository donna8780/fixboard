package com.study.board.domain.user.service;

import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CreateUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  //유저 생성
  public void createUser(CreateUserReqDto req) {
    userRepository.save(req.of(bCryptPasswordEncoder.encode(req.password())));
  }
}

