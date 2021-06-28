package serviceImpl;

import domain.Users;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // id 중복 검사
    @Override
    public String check_id(String id) {
        return userMapper.check_id(id);
    }

    // 휴대폰 중복 검사
    @Override
    public String check_phoneNumber(String phoneNumber) {
        return userMapper.check_phoneNumber(phoneNumber);
    }

    // 회원 가입
    @Override
    public String sign_up(Users user) {
        String return_id  = check_id(user.getId());
        if (return_id == null || return_id.isEmpty()){
            String return_phoneNumber = check_phoneNumber(user.getPhoneNumber());
            if(return_phoneNumber == null || return_phoneNumber.isEmpty()){
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                userMapper.sign_up(user);
                return "Membership Success";
            }
            else{
                return "There are members with duplicate phone numbers.";
            }
        }
        else{
            return "There is a member with a duplicate ID.";
        }
    }

    // 로그인
    @Override
    public String sign_in(Users user) {
        Users return_user = userMapper.sign_in(user);
        if(return_user != null){
            if(BCrypt.checkpw(user.getPassword(), return_user.getPassword())){
                return "login success";
            }
            else {
                return "login failed";
            }
        }
        else {
            return "hi";
        }
    }

    // ID 찾기
    @Override
    public String find_id(String name, String phoneNumber) {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        String return_id = userMapper.find_id(map);
        System.out.println(return_id);
        if (return_id == null || return_id.isEmpty()){
            return "The memeber ID you are looking for cannot be found";
        }
        else {
            return return_id;
        }
    }

    @Override
    public Users look_up(Users user) {
        return userMapper.look_up(user);
    }

    @Override
    public String change_password(String new_password){
        return "test";
    }
}