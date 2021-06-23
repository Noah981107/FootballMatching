package serviceImpl;

import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Users> read_users() {
        return userMapper.read_users();
    }

    @Override
    public String login_user(Users user) {
        String return_id = userMapper.login_user(user);
        if(return_id == null || return_id.isEmpty()){
            return "Login Failed";
        }
        else{
            return "Login Success";
        }
    }

    @Override
    public String sign_up(Users user) {
        String user_id = user.getId();
        String result  = userMapper.check_id(user_id);
        if (result == null || result.isEmpty()){
            userMapper.sign_up(user);
            return "Membership success";
        }
        else{
            return "There is a duplicate ID";
        }
    }
}
