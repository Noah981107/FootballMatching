package repository;

import domain.BusinessUsers;
import domain.Users;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BusinessUserMapper {
    String checkId(String id); // 아이디 중복 검사
    String checkPhoneNumber(String phoneNumber); // 전화번호 중복 검사
    void signUp(BusinessUsers b_user); // 회원가입
    BusinessUsers signIn(BusinessUsers b_user); // 로그인
    String findId(Map map); // ID 찾기
    BusinessUsers lookUp(BusinessUsers b_user); // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악
    void changePassword(BusinessUsers b_user); //비밀번호 찾기 - 비밀번호 변경
}
