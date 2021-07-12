package serviceImpl.non_auth;

import domain.BusinessUsers;
import exception.BusinessUserException;
import exception.ErrorCode;
import exception.FieldException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.BusinessUserMapper;
import repository.non_auth.FieldMapper;
import service.non_auth.BusinessUserService;
import util.JwtUtil;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired
    private BusinessUserMapper bUserMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private JwtUtil jwtUtil;

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

    // 구장 중복 확인
    @Override
    public String checkFieldId(int fieldId) {
        return bUserMapper.checkFieldId(fieldId);
    }

    //토큰 발급
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

    // 회원가입
    @Override
    public void signUp(BusinessUsers bUser) throws Exception {
        if(bUser.getId() == null || bUser.getId().equals("string")){
            throw new BusinessUserException(ErrorCode.Id_Is_Empty);
        }
        if(bUser.getPassword() == null || bUser.getPassword().equals("string")){
            throw new BusinessUserException(ErrorCode.Password_Is_Empty);
        }
        if(bUser.getPhoneNumber() == null || bUser.getPhoneNumber().equals("string")){
            throw new BusinessUserException(ErrorCode.PhoneNumber_Is_Empty);
        }
        if(bUser.getFieldName() == null || bUser.getFieldName().equals("string")){
            throw new BusinessUserException(ErrorCode.Field_Name_Is_Empty);
        }
        String returnId = checkId(bUser.getId()); // id 중복 확인
        if(returnId == null || returnId.isEmpty()){ // id가 중복되지 않았을때
            String returnPhoneNumber = checkPhoneNumber(bUser.getPhoneNumber()); // 휴대전화번호 중복 확인
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){ // 휴대전화번호가 중복되지 않았을 때
                int returnFieldId = checkFieldName(bUser.getFieldName()); // 구장 이름에 따른 구장 번호 조회
                if(returnFieldId > 0){ // 구장 번호가 있을 경우
                    String overlapFieldId = checkFieldId(returnFieldId); // 구장 중복 확인
                    if(overlapFieldId == null || overlapFieldId.isEmpty()){
                        bUser.setJoinDate(Timestamp.valueOf(LocalDateTime.now()).toString());
                        bUser.setPassword(BCrypt.hashpw(bUser.getPassword(), BCrypt.gensalt()));
                        bUser.setFieldName(Integer.toString(returnFieldId));
                        bUserMapper.signUp(bUser);
                    }
                    else{
                        throw new FieldException(ErrorCode.Filed_Already_Exists);
                    }
                }
                else{
                    throw new FieldException(ErrorCode.Registered_Field_Is_Empty);
                }
            }
            else{
                throw new BusinessUserException(ErrorCode.PhoneNumber_Already_Exists);
            }
        }
        else {
            throw new BusinessUserException(ErrorCode.Id_Already_Exists);
        }
    }

    //로그인
    @Override
    public String signIn(BusinessUsers bUser) throws Exception {
        if(bUser.getId() == null || bUser.getId().equals("string")){
            throw new BusinessUserException(ErrorCode.Id_Is_Empty);
        }
        if(bUser.getPassword() == null || bUser.getPassword().equals("string")){
            throw new BusinessUserException(ErrorCode.Password_Is_Empty);
        }
        BusinessUsers returnBUser = bUserMapper.signIn(bUser);
        if(returnBUser != null){
            if(BCrypt.checkpw(bUser.getPassword(), returnBUser.getPassword())){
                return tokenIssued(bUser.getId());
            }
            else{
                throw new BusinessUserException(ErrorCode.Password_Does_Not_Match);
            }
        }
        else{
            throw new BusinessUserException(ErrorCode.Id_Does_Not_Match);
        }
    }

    // ID 찾기
    @Override
    public String findId(String name, String phoneNumber, String fieldName) throws Exception {
        int returnFieldId = checkFieldName(fieldName);
        if(returnFieldId<0){
            throw new BusinessUserException(ErrorCode.Registered_Field_Is_Empty);
        }
        else{
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("phoneNumber", phoneNumber);
            map.put("field_id", returnFieldId);
            String returnId = bUserMapper.findId(map);
            if (returnId == null || returnId.isEmpty()){
               throw new BusinessUserException(ErrorCode.Id_Does_Not_Exists);
            }
            else {
                return returnId;
            }
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악
    @Override
    public BusinessUsers lookUp(BusinessUsers bUser) throws Exception {
        int returnFieldId = checkFieldName(bUser.getFieldName());
        if(returnFieldId>0){
            bUser.setFieldName(Integer.toString(returnFieldId));
            BusinessUsers returnBUser = bUserMapper.lookUp(bUser);
            if(returnBUser != null){
                returnBUser.setPassword("0");
                return returnBUser;
            }
            else{
                throw new BusinessUserException(ErrorCode.Member_Dose_Not_Exists);
            }
        }
        else{
            throw new FieldException(ErrorCode.Registered_Field_Is_Empty);
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
