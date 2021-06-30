package serviceImpl;

import domain.BusinessUsers;
import io.swagger.models.auth.In;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BusinessUserMapper;
import repository.FieldMapper;
import service.BusinessUserService;

import java.util.HashMap;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired
    private BusinessUserMapper b_userMapper;

    @Autowired
    private FieldMapper fieldMapper;

    // id 중복 체크
    @Override
    public String check_id(String id) {
        return b_userMapper.check_id(id);
    }

    // 전화번호 중복 체크
    @Override
    public String check_phoneNumber(String phoneNumber) {
        return b_userMapper.check_phoneNumber(phoneNumber);
    }

    // 구장 확인
    @Override
    public int check_fieldName(String field_name) {
        return fieldMapper.check_fieldName(field_name);
    }

    // 회원가입
    @Override
    public String sign_up(BusinessUsers b_user) {
        String return_id = check_id(b_user.getId());
        System.out.println("return_id : " + return_id);
        if(return_id == null || return_id.isEmpty()){
            String return_phoneNumber = check_phoneNumber(b_user.getPhoneNumber());
            System.out.println("return_phoneNumber : " + return_phoneNumber);
            if(return_phoneNumber == null || return_phoneNumber.isEmpty()){
                int return_fieldId = check_fieldName(b_user.getField_name());
                System.out.println("return_fieldID : " + return_fieldId);
                if(return_fieldId > 0){
                    b_user.setPassword(BCrypt.hashpw(b_user.getPassword(), BCrypt.gensalt()));
                    b_user.setField_name(Integer.toString(return_fieldId));
                    System.out.println("id : "+ b_user.getId());
                    System.out.println("password : "+ b_user.getPassword());
                    System.out.println("name : "+ b_user.getName());
                    System.out.println("phoneNumber : "+ b_user.getPhoneNumber());
                    System.out.println("fieldname : "+ b_user.getField_name());
                    b_userMapper.sign_up(b_user);
                    return "Membership Success";
                }
                else{
                    return "The selected stadium does not exist.";
                }
            }
            else{
                return "There are members with duplicate phone numbers.";
            }
        }
        else {
            return "There is a member with a duplicate ID.";
        }
    }

    //로그인
    @Override
    public String sign_in(BusinessUsers b_user) {
        BusinessUsers return_b_user = b_userMapper.sign_in(b_user);
        if(return_b_user != null){
            if(BCrypt.checkpw(b_user.getPassword(), return_b_user.getPassword())){
                return "Login Success";
            }
            else{
                return "Login Failed";
            }
        }
        else{
            return "ID does not match";
        }
    }

    // ID 찾기
    @Override
    public String find_id(String name, String phoneNumber, String field_name) {
        int return_fieldID = check_fieldName(field_name);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        map.put("field_id", Integer.toString(return_fieldID));
        String return_id = b_userMapper.find_id(map);
        if (return_id == null || return_id.isEmpty()){
            return "The memeber ID you are looking for cannot be found";
        }
        else {
            return return_id;
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악
    @Override
    public BusinessUsers look_up(BusinessUsers b_user) {
        int return_fieldID = check_fieldName(b_user.getField_name());
        System.out.println(return_fieldID);
        if(return_fieldID>0){
            b_user.setField_name(Integer.toString(return_fieldID));
            return b_userMapper.look_up(b_user);
        }
        else{
            return null;
        }
    }

    // 비밀번호 찾기 - 비밀번호 변경
    @Override
    public void change_password(BusinessUsers b_user) {
        System.out.println(b_user.getId());
        System.out.println(b_user.getPassword());
        int return_fieldID = check_fieldName(b_user.getField_name());
        System.out.println("여기요 : " + return_fieldID);
        b_user.setField_name(Integer.toString(return_fieldID));
        b_user.setPassword(BCrypt.hashpw(b_user.getPassword(), BCrypt.gensalt()));
        System.out.println(b_user.getPassword());
        b_userMapper.change_password(b_user);
    }
}
