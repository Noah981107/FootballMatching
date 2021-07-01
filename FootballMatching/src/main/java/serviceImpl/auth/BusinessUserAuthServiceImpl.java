package serviceImpl.auth;

import domain.BusinessUsers;
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

    @Override
    public String modification(String token, BusinessUsers bUser) {
        String name = bUser.getName();
        String password = bUser.getPassword();
        String phoneNumber = bUser.getPhoneNumber();
        String account = jwtUtil.getId(token);
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
        }
        if(password != null){
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            map.put("password", password);
            bUserAuthMapper.updatePassword(map);
        }
        return "hi";
    }

    @Override
    public BusinessUsers inquiry(String token) {
        String id = jwtUtil.getId(token);
        return bUserAuthMapper.inquiry(id);
    }

    public void withdraw(String token){
        String id = jwtUtil.getId(token);
        bUserAuthMapper.withdraw(id);
    }
}
