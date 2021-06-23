package service;

import domain.Users;

import java.util.List;

public interface UserService {
    List<Users> read_users();
    String login_user(Users user);
    String sign_up(Users user);
}
