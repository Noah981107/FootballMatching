package repository;

import domain.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String check_id(String id);
    String check_phoneNumber(String phoneNumber);
    void sign_up(Users user);
}
