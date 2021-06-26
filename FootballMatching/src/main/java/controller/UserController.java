package controller;

import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    //테스트 용 API
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    //일반 회원 가입 API
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity sign_up(@RequestBody Users user){
        return new ResponseEntity(userService.sign_up(user), HttpStatus.OK);
    }

    //일반 회원 로그인 API
    //@ResponseBody
    //@RequestMapping(value = "/signin", method = RequestMethod.POST)
    //public ResponseEntity sign_in(@RequestBody Users user){
    //   return new ResponseEntity(userService.login_user(user),HttpStatus.OK);
    //}
}
