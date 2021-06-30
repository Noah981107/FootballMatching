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
    public ResponseEntity sign_up(@RequestBody BusinessUsers b_user){
        return new ResponseEntity(b_userService.sign_up(b_user), HttpStatus.OK);
    }

    // 사업자 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity sign_in(@RequestBody BusinessUsers b_user){
        return new ResponseEntity(b_userService.sign_in(b_user), HttpStatus.OK);
    }

    // 사업자 회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    public ResponseEntity find_id(@RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "field-name") String field_name){
        return new ResponseEntity(b_userService.find_id(name,phoneNumber,field_name), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/finding/password", method = RequestMethod.POST)
    public BusinessUsers find_password(@RequestBody BusinessUsers b_user){
        BusinessUsers return_b_user = b_userService.look_up(b_user);
        System.out.println(return_b_user.getId() );
        if(return_b_user != null){
            return_b_user.setPassword("0");
            return return_b_user;
        }
        else{
            return null;
        }
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/changing/password", method = RequestMethod.POST)
    public void change_password(@RequestBody BusinessUsers b_user){
        b_userService.change_password(b_user);
    }
}
