package serviceImpl.auth;

import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.UserAuthMapper;
import service.UserService;
import service.auth.UserAuthService;
import util.JwtUtil;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserService userService;

    @Autowired(required=false)
    private JwtUtil jwtUtil;

    //회원 정보 수정 - 이름, 비밀번호, 전화번호 수정
    @Override
    public String modification(String token, Users user) {
        String return_phoneNumber = userService.check_phoneNumber(user.getPhoneNumber());
        if(return_phoneNumber != null || return_phoneNumber.isEmpty()){
            user.setId(jwtUtil.get_id(token));
            userAuthMapper.modification(user);
            return "Member Information Modification Success";
        }
        else{
            return "There are members with duplicate phone numbers.";
        }
    }
}
