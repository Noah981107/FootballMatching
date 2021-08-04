package repository.non_auth;

import domain.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String checkId(String id); // 아이디 중복 검사
    String checkPhoneNumber(String phoneNumber); // 전화번호 중복 검사
    void signUp(Users user); // 회원 가입
    String signIn(Users user); // 로그인
    String findId(Users user); // ID 찾기
    Users lookUp(Users user); // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    void changePassword(Users user); //비밀번호 찾기 - 비밀번호 변경
    String findIdx(String id); // idx 찾기
}
