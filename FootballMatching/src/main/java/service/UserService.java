package service;

import domain.Users;

public interface UserService {
    String check_id(String id); // id 중복 체크
    String check_phoneNumber(String phoneNumber); // 전화번호 중복 체크
    String sign_up(Users user); // 회원가입
    String sign_in(Users user); // 로그인
    String find_id(String name, String phoneNumber); // id 찾기
    Users look_up(Users user); // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    void change_password(Users user); // 비밀번호 찾기 - 비밀번호 변경
}
