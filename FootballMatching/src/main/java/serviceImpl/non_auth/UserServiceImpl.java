package serviceImpl.non_auth;

import domain.Users;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.UserMapper;
import service.non_auth.UserService;
import util.JwtUtil;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    // id 중복 검사
    @Override
    public String checkId(String id) {
        return userMapper.checkId(id);
    }

    // 휴대폰 중복 검사
    @Override
    public String checkPhoneNumber(String phoneNumber) {
        return userMapper.checkPhoneNumber(phoneNumber);
    }

    // 토큰 발급
    @Override
    public String tokenIssued(String id) {
        String return_id = checkId(id);
        if(return_id == null || return_id.isEmpty()){
            try {
                throw new Exception("There is no such id");
            } catch (Exception e) {
                e.printStackTrace();
                return "ID is None";
            }
        }
        else{
            return jwtUtil.token_issued(return_id);
        }
    }

    // 회원 가입
    @Override
    public String signUp(Users user) {
        System.out.println("여기요");
        System.out.println(user.getId());
        System.out.println(user.getPassword());
        String return_id  = checkId(user.getId());
        System.out.println(return_id);
        if (return_id == null || return_id.isEmpty()){
            String return_phoneNumber = checkPhoneNumber(user.getPhoneNumber());
            if(return_phoneNumber == null || return_phoneNumber.isEmpty()){
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                userMapper.signUp(user);
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
    public String signIn(Users user) {
        Users return_user = userMapper.signIn(user);
        if(return_user != null){
            if(BCrypt.checkpw(user.getPassword(), return_user.getPassword())){
                return tokenIssued(return_user.getId());
            }
            else {
                return "login failed";
            }
        }
        else {
            return "ID does not match";
        }
    }

    // ID 찾기
    @Override
    public String findId(String name, String phoneNumber) {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        String return_id = userMapper.findId(map);
        if (return_id == null || return_id.isEmpty()){
            return "The memeber ID you are looking for cannot be found";
        }
        else {
            return return_id;
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    @Override
    public Users lookUp(Users user) {
        Users return_user = userMapper.lookUp(user);
        if(return_user != null){
            return_user.setPassword("0");
            return return_user;
        }
        return null;
    }

    // 비밀번호 찾기 - 비밀번호 변경
    @Override
    public void changePassword(Users user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userMapper.changePassword(user);
    }
}