package serviceImpl.non_auth;

import domain.Users;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.UserMapper;
import service.non_auth.UserService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        String returnId = checkId(id);
        if(returnId == null || returnId.isEmpty()){
            try {
                throw new Exception("There is no such id");
            } catch (Exception e) {
                e.printStackTrace();
                return "ID is None";
            }
        }
        else{
            return jwtUtil.tokenIssued(returnId);
        }
    }

    // 회원 가입
    @Override
    public String signUp(Users user) {
        String returnId  = checkId(user.getId());
        if (returnId == null || returnId.isEmpty()){
            String returnPhoneNumber = checkPhoneNumber(user.getPhoneNumber());
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){
                user.setJoinDate(Timestamp.valueOf(LocalDateTime.now()).toString());
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
        String returnPassword = userMapper.signIn(user);
        if(returnPassword != null){
            if(BCrypt.checkpw(user.getPassword(), returnPassword)){
                return "Login Success\n" + tokenIssued(user.getId());
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
        String returnId = userMapper.findId(map);
        if (returnId == null || returnId.isEmpty()){
            return "The memeber ID you are looking for cannot be found";
        }
        else {
            return returnId;
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    @Override
    public Users lookUp(Users user) {
        Users returnUser = userMapper.lookUp(user);
        if(returnUser != null){
            returnUser.getJoinDate();
            returnUser.setPassword("0");
            return returnUser;
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