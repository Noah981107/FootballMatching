package repository.auth;

import domain.BusinessUsers;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BusinessUserAuthMapper {
    void updateName(Map map);        // 회원 정보 변경 - 이름
    void updatePhoneNumber(Map map); // 회원 정보 변경 - 전화번호
    void updatePassword(Map map);    // 회원 정보 변경 - 비밀번호
    BusinessUsers inquiry(String id);
    void withdraw(String id);
}
