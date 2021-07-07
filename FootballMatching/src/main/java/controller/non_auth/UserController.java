package controller.non_auth;

import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.non_auth.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    //일반 회원 가입 API
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody Users user){
        return new ResponseEntity(userService.signUp(user), HttpStatus.OK);
    }

    //일반 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody Users user){
        System.out.println("id" +  user.getId());
        System.out.println("pw" + user.getPassword());
        return new ResponseEntity(userService.signIn(user),HttpStatus.OK);
    }

    //회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    public ResponseEntity findId(@RequestParam(value = "name") String name ,
                                 @RequestParam(value = "phoneNumber") String phoneNumber){
        return new ResponseEntity(userService.findId(name, phoneNumber), HttpStatus.OK);
    }


    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/finding/password", method = RequestMethod.POST)
    public ResponseEntity findPassword(@RequestBody Users user){
        return new ResponseEntity(userService.lookUp(user), HttpStatus.OK) ;
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/update/password", method = RequestMethod.PATCH)
    public void changePassword(@RequestBody Users user){
        userService.changePassword(user);
    }

}
