package service;

import domain.Users;

import java.util.List;

public interface UserService {
    String check_id(String id); // id 중복 체크
    String check_phoneNumber(String phoneNumber); // 전화번호 중복 체크
    String sign_up(Users user); // 회원가입
    //String login_user(Users user); // 로그인
}
