package repository;

import domain.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<Users> read_users();
    String login_user(Users user);
    String sign_up(Users user);
    String check_id(String user_id);
}
