package service.auth;

import domain.Users;

public interface UserAuthService {
    void modification(Users user) throws Exception; // 회원 정보 수정 - 비밀번호, 이름 , 전화번호 수정 가능
    Users inquiry(); // 내 정보 조회
    void withdraw(); // 회원 탈퇴
}
