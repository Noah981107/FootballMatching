package serviceImpl.non_auth;

import domain.Users;
import exception.ErrorCode;
import exception.UserException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import repository.non_auth.UserMapper;
import service.non_auth.UserService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    //  중복 검사
    @Override
    public String checkId(String id) {
        return userMapper.checkId(id);
    }

    // 휴대폰 중복 검사
    @Override
    public String checkPhoneNumber(String phoneNumber) {
        return userMapper.checkPhoneNumber(phoneNumber);
    }

    // idx 찾기
    @Override
    public String findIdx(String id) {
        return userMapper.findIdx(id);
    }

    // 토큰 발급
    @Override
    public String tokenIssued(String id) throws Exception {
        String returnId = checkId(id); // id 확인
        if(returnId == null || returnId.isEmpty()){ // id가 없으면
            throw new UserException(ErrorCode.Id_Does_Not_Exists);
        }
        else{
            return jwtUtil.tokenIssued(returnId); // id에 따른 토큰 발급
        }
    }

    // 회원 가입
    @Override
    public void signUp(Users user) throws Exception {
        if(user.getId() == null || user.getId().equals("string")){
            throw new UserException(ErrorCode.Id_Is_Empty);
        }
        if(user.getPassword() == null || user.getPassword().equals("string")){
            throw new UserException(ErrorCode.Password_Is_Empty);
        }
        if(user.getPhoneNumber() == null || user.getPhoneNumber().equals("string")){
            throw new UserException(ErrorCode.PhoneNumber_Is_Empty);
        }
        String returnId  = checkId(user.getId()); // id 중복 확인
        if (returnId == null || returnId.isEmpty()){ // 중복된 id가 없을 때
            String returnPhoneNumber = checkPhoneNumber(user.getPhoneNumber()); // 휴대전화번호 중복 확인
            if(returnPhoneNumber == null || returnPhoneNumber.isEmpty()){ // 중복된 휴대전화번호가 없을 때때
                user.setJoinDate(Timestamp.valueOf(LocalDateTime.now()).toString()); // 가입날짜 저장
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); // 비밀번호 저장
                userMapper.signUp(user);
            }
            else{
                throw new UserException(ErrorCode.PhoneNumber_Already_Exists);
            }
        }
        else{
            throw new UserException(ErrorCode.Id_Already_Exists);
        }
    }

    // 로그인
    @Override
    public String signIn(Users user) throws Exception{
        if(user.getId() == null || user.getId().equals("string")) {
            throw new UserException(ErrorCode.Id_Is_Empty);
        }
        if(user.getPassword() == null || user.getPassword().equals("string")){
            throw new UserException(ErrorCode.Password_Is_Empty);
        }
        String returnPassword = userMapper.signIn(user); // DB에서 비밀번호 반환
        if(returnPassword != null){
            if(BCrypt.checkpw(user.getPassword(), returnPassword)){ // 비밀번호를 비교
                return tokenIssued(user.getId()); // 로그인 성공 시 토큰 반환
            }
            else {
                throw new UserException(ErrorCode.Password_Does_Not_Match);
            }
        }
        else {
            throw new UserException(ErrorCode.Id_Does_Not_Match);
        }
    }

    // ID 찾기
    @Override
    public String findId(String name, String phoneNumber) throws Exception {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("phoneNumber", phoneNumber);
        String returnId = userMapper.findId(map); // name, phoneNumber 비교해서 id 찾아오기
        if (returnId == null || returnId.isEmpty()){
            throw new UserException(ErrorCode.Id_Does_Not_Exists);
        }
        else {
            return returnId; // id가 있으면 찾은 id 반환
        }
    }

    // 비밀번호 찾기 - id, 전화번호, 이름 일치 여부 파악
    @Override
    public Users lookUp(Users user) throws Exception {

        if(user.getId() == null || user.getId().equals("string")){
            throw new UserException(ErrorCode.Id_Is_Empty);
        }
        if(user.getPhoneNumber() == null || user.getPhoneNumber().equals("string")){
            throw new UserException(ErrorCode.PhoneNumber_Is_Empty);
        }
        if(user.getName() == null || user.getName().equals("string")){
            throw new UserException(ErrorCode.Name_Is_Empty);
        }
        Users returnUser = userMapper.lookUp(user); // id, 전화번호, 이름 일치 여부를 파악
        if(returnUser != null){
            returnUser.setPassword("0"); // 찾았을때, 비밀번호를 0으로 초기화해서 Json으로 쏴줌
            return returnUser;
        }
        else {
            throw new UserException(ErrorCode.Member_Dose_Not_Exists);
        }
    }

    // 비밀번호 찾기 - 비밀번호 변경
    @Override
    public void changePassword(Users user){ // 0으로 초기화된 Json에서 비밀번호를 바꿔서 사용
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); // 암호화
        userMapper.changePassword(user);
    }
}