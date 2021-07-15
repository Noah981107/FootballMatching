package serviceImpl.auth;

import domain.Users;
import exception.ErrorCode;
import exception.UserException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.auth.UserAuthMapper;
import service.non_auth.UserService;
import service.auth.UserAuthService;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
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
    public void modification(String token, Users user) throws Exception {
        String name = user.getName();
        String passWord = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        String account = jwtUtil.getId(token);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", account);
        if (name != null && !name.equals("string")){
            map.put("name", name);
            userAuthMapper.updateName(map);
        }
        if(phoneNumber != null && !phoneNumber.equals("string")){
            String returnPhoneNumber = userService.checkPhoneNumber(phoneNumber);
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){
                map.put("phoneNumber", phoneNumber);
                userAuthMapper.updatePhoneNumber(map);
            }
            else{
                throw new UserException(ErrorCode.PhoneNumber_Already_Exists);
            }
        }
        if(passWord != null){
            passWord = BCrypt.hashpw(passWord, BCrypt.gensalt());
            map.put("password", passWord);
            userAuthMapper.updatePassword(map);
        }
    }

    // 자신의 정보 조회
    @Override
    public Users inquiry() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        String id = jwtUtil.getId(token);
        System.out.println(request);
        System.out.println(token);
        System.out.println(id);
        return userAuthMapper.inquiry(id);
    }

    // 회원 탈퇴
    @Override
    public void withdraw(String token) {
        String id = jwtUtil.getId(token);
        userAuthMapper.withdraw(id);
    }
}
