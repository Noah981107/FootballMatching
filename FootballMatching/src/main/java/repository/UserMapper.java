package repository;

import domain.Users;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    String check_id(String id); // 아이디 중복 검사
    String check_phoneNumber(String phoneNumber); // 전화번호 중복 검사
    void sign_up(Users user); // 회원 가입
    Users sign_in(Users user); // 로그인
    String find_id(Map map); // ID 찾기
    Users look_up(Users user); // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    void change_password(Users users); //비밀번호 찾기 - 비밀번호 변경
}
