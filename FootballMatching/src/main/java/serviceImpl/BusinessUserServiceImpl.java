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
    public String checkId(String id) {
        return b_userMapper.checkId(id);
    }

    // 전화번호 중복 체크
    @Override
    public String checkPhoneNumber(String phoneNumber) {
        return b_userMapper.checkPhoneNumber(phoneNumber);
    }

    // 구장 확인
    @Override
    public int checkFieldName(String field_name) {
        return fieldMapper.checkFieldName(field_name);
    }

    // 회원가입
    @Override
    public String signUp(BusinessUsers b_user) {
        String return_id = checkId(b_user.getId());
        System.out.println("return_id : " + return_id);
        if(return_id == null || return_id.isEmpty()){
            String return_phoneNumber = checkPhoneNumber(b_user.getPhoneNumber());
            System.out.println("return_phoneNumber : " + return_phoneNumber);
            if(return_phoneNumber == null || return_phoneNumber.isEmpty()){
                int return_fieldId = checkFieldName(b_user.getField_name());
                System.out.println("return_fieldID : " + return_fieldId);
                if(return_fieldId > 0){
                    b_user.setPassword(BCrypt.hashpw(b_user.getPassword(), BCrypt.gensalt()));
                    b_user.setField_name(Integer.toString(return_fieldId));
                    System.out.println("id : "+ b_user.getId());
                    System.out.println("password : "+ b_user.getPassword());
                    System.out.println("name : "+ b_user.getName());
                    System.out.println("phoneNumber : "+ b_user.getPhoneNumber());
                    System.out.println("fieldname : "+ b_user.getField_name());
                    b_userMapper.signUp(b_user);
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
    public String signIn(BusinessUsers b_user) {
        BusinessUsers return_b_user = b_userMapper.signIn(b_user);
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
    public String findId(String name, String phoneNumber, String field_name) {
        int return_fieldID = checkFieldName(field_name);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        map.put("field_id", Integer.toString(return_fieldID));
        String return_id = b_userMapper.findId(map);
        if (return_id == null || return_id.isEmpty()){
            return "The memeber ID you are looking for cannot be found";
        }
        else {
            return return_id;
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악
    @Override
    public BusinessUsers lookUp(BusinessUsers b_user) {
        int return_fieldID = checkFieldName(b_user.getField_name());
        System.out.println(return_fieldID);
        if(return_fieldID>0){
            b_user.setField_name(Integer.toString(return_fieldID));
            BusinessUsers return_b_user = b_userMapper.lookUp(b_user);
            if(return_b_user != null){
                return_b_user.setPassword("0");
                return return_b_user;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    // 비밀번호 찾기 - 비밀번호 변경
    @Override
    public void changePassword(BusinessUsers b_user) {
        System.out.println(b_user.getId());
        System.out.println(b_user.getPassword());
        int return_fieldID = checkFieldName(b_user.getField_name());
        System.out.println("여기요 : " + return_fieldID);
        b_user.setField_name(Integer.toString(return_fieldID));
        b_user.setPassword(BCrypt.hashpw(b_user.getPassword(), BCrypt.gensalt()));
        System.out.println(b_user.getPassword());
        b_userMapper.changePassword(b_user);
    }
}
