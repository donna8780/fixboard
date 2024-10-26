package com.study.board.domain.user.service;


import com.study.board.domain.user.dto.req.LoginReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.domain.user.entity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  //로그인 기능
  public void loginUser(LoginReqDto req, HttpSession session){
    User byUsername = userRepository.findByUsername(req.username());

    if(byUsername == null || !passwordEncoder.matches(req.rawPassword(), byUsername.getPassword())){
        throw new RuntimeException("로그인 실패");
    }
    session.setAttribute("user", byUsername);
    }
  }


