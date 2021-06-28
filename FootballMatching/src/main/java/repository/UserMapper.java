package repository;

import domain.Users;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    String check_id(String id);
    String check_phoneNumber(String phoneNumber);
    void sign_up(Users user);
    Users sign_in(Users user);
    String find_id(Map map);
    Users look_up(Users user);
    void change_password(Users user);
}
