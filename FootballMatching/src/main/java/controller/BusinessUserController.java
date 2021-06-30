package controller;

import domain.BusinessUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BusinessUserService;

@Controller
@RequestMapping(value = "/business-user")
public class BusinessUserController {

    @Autowired
    private BusinessUserService b_userService;

    // 사업자 회원 회원가입 API
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody BusinessUsers b_user){
        return new ResponseEntity(b_userService.signUp(b_user), HttpStatus.OK);
    }

    // 사업자 회원 로그인 API - 로그인할때 토큰도 발급해줘야함! 수정 필요
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody BusinessUsers b_user){
        return new ResponseEntity(b_userService.signIn(b_user), HttpStatus.OK);
    }

    // 사업자 회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    public ResponseEntity findID(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "field-name") String field_name){
        return new ResponseEntity(b_userService.findId(name,phoneNumber,field_name), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/finding/password", method = RequestMethod.POST)
    public ResponseEntity findPassword(@RequestBody BusinessUsers b_user){
        return new ResponseEntity(b_userService.lookUp(b_user), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/update/password", method = RequestMethod.PATCH)
    public void change_password(@RequestBody BusinessUsers b_user){
        b_userService.changePassword(b_user);
    }
}
