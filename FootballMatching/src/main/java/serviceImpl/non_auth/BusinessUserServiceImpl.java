package serviceImpl.non_auth;

import domain.BusinessUsers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.BusinessUserMapper;
import repository.non_auth.FieldMapper;
import service.non_auth.BusinessUserService;

import java.util.HashMap;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired
    private BusinessUserMapper bUserMapper;

    @Autowired
    private FieldMapper fieldMapper;

    // id 중복 체크
    @Override
    public String checkId(String id) {
        return bUserMapper.checkId(id);
    }

    // 전화번호 중복 체크
    @Override
    public String checkPhoneNumber(String phoneNumber) {
        return bUserMapper.checkPhoneNumber(phoneNumber);
    }

    // 구장 확인
    @Override
    public int checkFieldName(String fieldName) {
        return fieldMapper.checkFieldName(fieldName);
    }

    // 회원가입
    @Override
    public String signUp(BusinessUsers bUser) {
        String returnId = checkId(bUser.getId());
        if(returnId == null || returnId.isEmpty()){
            String returnPhoneNumber = checkPhoneNumber(bUser.getPhoneNumber());
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){
                int returnFieldId = checkFieldName(bUser.getFieldName());
                if(returnFieldId > 0){
                    bUser.setPassword(BCrypt.hashpw(bUser.getPassword(), BCrypt.gensalt()));
                    bUser.setFieldName(Integer.toString(returnFieldId));
                    bUserMapper.signUp(bUser);
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
    public String signIn(BusinessUsers bUser) {
        BusinessUsers returnBUser = bUserMapper.signIn(bUser);
        if(returnBUser != null){
            if(BCrypt.checkpw(bUser.getPassword(), returnBUser.getPassword())){
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
    public String findId(String name, String phoneNumber, String fieldName) {
        int returnFieldId = checkFieldName(fieldName);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        map.put("field_id", Integer.toString(returnFieldId));
        String returnId = bUserMapper.findId(map);
        if (returnId == null || returnId.isEmpty()){
            return "The memeber ID you are looking for cannot be found";
        }
        else {
            return returnId;
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악
    @Override
    public BusinessUsers lookUp(BusinessUsers bUser) {
        int returnFieldId = checkFieldName(bUser.getFieldName());
        if(returnFieldId>0){
            bUser.setFieldName(Integer.toString(returnFieldId));
            BusinessUsers returnBUser = bUserMapper.lookUp(bUser);
            if(returnBUser != null){
                returnBUser.setPassword("0");
                return returnBUser;
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
    public void changePassword(BusinessUsers bUser) {
        int returnFieldId = checkFieldName(bUser.getFieldName());
        bUser.setFieldName(Integer.toString(returnFieldId));
        bUser.setPassword(BCrypt.hashpw(bUser.getPassword(), BCrypt.gensalt()));
        bUserMapper.changePassword(bUser);
    }
}
