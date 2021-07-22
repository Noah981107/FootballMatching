package serviceImpl.auth;

import domain.Users;
import exception.ErrorCode;
import exception.UserException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    @Override
    public void modification(Users user) throws Exception {
        String name = user.getName(); // 수정된 이름
        String passWord = user.getPassword(); // 수정된 비밀번호
        String phoneNumber = user.getPhoneNumber(); // 수정된 전화번호
        String account = jwtUtil.getId(); // 토큰으로부터 얻은 id
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", account);
        if (name != null && !name.equals("string")){ // 이름이 수정되어있으면
            map.put("name", name);
            userAuthMapper.updateName(map); // 이름 수정
        }
        if(phoneNumber != null && !phoneNumber.equals("string")){ // 전화번호가 수정되어 있으면
            String returnPhoneNumber = userService.checkPhoneNumber(phoneNumber); // 전화번호 중복 검사
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){ // 전화번호가 중복되지 않다면
                map.put("phoneNumber", phoneNumber);
                userAuthMapper.updatePhoneNumber(map); // 전화번호 수정
            }
            else{
                throw new UserException(ErrorCode.PhoneNumber_Already_Exists); // 전화번호가 중복됨
            }
        }
        if(passWord != null && !passWord.equals("string")){ // 비밀번호가 수정되어 있으면
            passWord = BCrypt.hashpw(passWord, BCrypt.gensalt()); // 비밀번호 암호화
            map.put("password", passWord);
            userAuthMapper.updatePassword(map); // 비밀번호 수정
        }
    }

    // 자신의 정보 조회
    @Override
    public Users inquiry() {
        return userAuthMapper.inquiry(jwtUtil.getId()); // 토큰으로부터 얻은 id로 정보 조회
    }

    // 회원 탈퇴
    @Override
    public void withdraw() {
        userAuthMapper.withdraw(jwtUtil.getId());
    }
}
