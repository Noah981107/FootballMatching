package repository.auth;

import domain.Users;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserAuthMapper {
    void updateName(Map map);        // 회원 정보 변경 - 이름
    void updatePhoneNumber(Map map); // 회원 정보 변경 - 전화번호
    void updatePassword(Map map);    // 회원 정보 변경 - 비밀번호
    Users inquiry(String id);        // 자신의 정보 조회
    void withdraw(String id);        // 회원 탈퇴
}
