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

    // id 중복 검사
    @Override
    public String check_id(String id) {
        return userMapper.check_id(id);
    }

    @Override
    public String check_phoneNumber(String phoneNumber) {
        return userMapper.check_phoneNumber(phoneNumber);
    }

    // 회원 가입
    @Override
    public String sign_up(Users user) {
        String id = user.getId();
        System.out.println(user.getId());
        System.out.println(user.getPassword());
        System.out.println(user.getName());
        System.out.println(user.getPhoneNumber());
        System.out.println(user.getLocation_code());
        System.out.println(user.getJoinDate());
        System.out.println(user.getIs_deleted());

        String return_id  = check_id(id);
        if (return_id == null || return_id.isEmpty()){
            String phoneNumber = user.getPhoneNumber();
            String return_phoneNumber = check_phoneNumber(phoneNumber);
            if(return_phoneNumber == null || return_phoneNumber.isEmpty()){
                userMapper.sign_up(user);
                return "회원가입 성공!1";
            }
            else{
                return "전화번호가 중복된 회원이 있습니다.2";
            }
        }
        else{
            return "아이디가 중복된 회원이 있습니다.3";
        }
    }

    /*@Override
    public String login_user(Users user) {
        String return_id = userMapper.login_user(user);
        if(return_id == null || return_id.isEmpty()){
            return "Login Failed";
        }
        else{
            return "Login Success";
        }
    }*/
}
