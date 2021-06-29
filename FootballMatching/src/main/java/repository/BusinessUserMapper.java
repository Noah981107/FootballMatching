package repository;

import domain.BusinessUsers;
import domain.Users;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BusinessUserMapper {
    String check_id(String id); // 아이디 중복 검사
    String check_phoneNumber(String phoneNumber); // 전화번호 중복 검사
    void sign_up(BusinessUsers b_user);
}
