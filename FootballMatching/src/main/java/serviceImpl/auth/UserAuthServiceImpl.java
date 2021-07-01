package serviceImpl.auth;

import domain.Users;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.UserAuthMapper;
import service.non_auth.UserService;
import service.auth.UserAuthService;
import util.JwtUtil;

import java.util.HashMap;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    //회원 정보 수정 - 비밀번호, 이름 , 전화번호 수정 가능
    @Override
    public String modification(String token, Users user) {

        String name = user.getName();
        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        String account = jwtUtil.getId(token);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", account);
        if (name != null){
            map.put("name", name);
            userAuthMapper.updateName(map);
        }
        if(phoneNumber != null){
            String returnPhoneNumber = userService.checkPhoneNumber(phoneNumber);
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){
                map.put("phoneNumber", phoneNumber);
                userAuthMapper.updatePhoneNumber(map);
            }
            // 휴대폰 중복되었을때 오류 처리
        }
        if(password != null){
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            map.put("password", password);
            userAuthMapper.updatePassword(map);
        }
        return "hi";
    }

    @Override
    public Users inquiry(String token) {
        String id = jwtUtil.getId(token);
        return userAuthMapper.inquiry(id);
    }

    @Override
    public void withdraw(String token) {
        String id = jwtUtil.getId(token);
        userAuthMapper.withdraw(id);
    }
}
