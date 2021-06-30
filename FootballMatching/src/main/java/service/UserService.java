package service;

import domain.Users;

public interface UserService {
    String checkId(String id); // id 중복 체크
    String checkPhoneNumber(String phoneNumber); // 전화번호 중복 체크
    String tokenIssued(String id); // 토큰 발급
    String signUp(Users user); // 회원가입
    String signIn(Users user); // 로그인
    String findId(String name, String phoneNumber); // id 찾기
    Users lookUp(Users user); // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    void changePassword(Users user); // 비밀번호 찾기 - 비밀번호 변경
}
