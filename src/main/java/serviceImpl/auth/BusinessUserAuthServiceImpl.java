package serviceImpl.auth;

import domain.BusinessUsers;
import exception.BusinessUserException;
import exception.ErrorCode;
import exception.UserException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.BusinessUserAuthMapper;
import service.auth.BusinessUserAuthService;
import service.non_auth.BusinessUserService;
import util.JwtUtil;

import java.util.HashMap;

@Service
public class BusinessUserAuthServiceImpl implements BusinessUserAuthService {

    @Autowired
    private BusinessUserAuthMapper bUserAuthMapper;

    @Autowired
    private BusinessUserService bUserService;

    @Autowired
    private JwtUtil jwtUtil;

    //회원 정보 수정 - 비밀번호, 이름 , 전화번호 수정 가능
    @Override
    public void modification(BusinessUsers bUser) {
        String name = bUser.getName();
        String password = bUser.getPassword();
        String phoneNumber = bUser.getPhoneNumber();
        String account = jwtUtil.getId();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", account);
        if (name != null) {
            map.put("name", name);
            bUserAuthMapper.updateName(map);
        }
        if(phoneNumber != null){
            String returnPhoneNumber = bUserService.checkPhoneNumber(phoneNumber);
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){
                map.put("phoneNumber", phoneNumber);
                bUserAuthMapper.updatePhoneNumber(map);
            }
            else{
                throw new BusinessUserException(ErrorCode.PhoneNumber_Already_Exists);
            }
        }
        if(password != null){
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            map.put("password", password);
            bUserAuthMapper.updatePassword(map);
        }
    }

    // 자신의 정보 조회
    @Override
    public BusinessUsers inquiry() {
        return bUserAuthMapper.inquiry(jwtUtil.getId());
    }

    // 회원 탈퇴
    public void withdraw(){
        bUserAuthMapper.withdraw(jwtUtil.getId());
    }
}
