package controller;

import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    //일반 회원 가입 API
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity sign_up(@RequestBody Users user){
        return new ResponseEntity(userService.sign_up(user), HttpStatus.OK);
    }

    //일반 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity sign_in(@RequestBody Users user){
        return new ResponseEntity(userService.sign_in(user),HttpStatus.OK);
    }

    //회원 ID 찾기 API - 요고 수정 좀 필요함
    @ResponseBody
    @RequestMapping(value = "finding", method = RequestMethod.GET)
    public ResponseEntity find_id(@RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber){
        System.out.println(name);
        System.out.println(phoneNumber);
        return new ResponseEntity(userService.find_id(name, phoneNumber), HttpStatus.OK);
    }
}
