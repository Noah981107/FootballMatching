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

    //회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    public ResponseEntity find_id(@RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber){
        return new ResponseEntity(userService.find_id(name, phoneNumber), HttpStatus.OK);
    }


    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인 - 수정 필요
    @ResponseBody
    @RequestMapping(value = "/finding/password", method = RequestMethod.POST)
    public ResponseEntity find_password(@RequestBody Users user){
        Users return_user = userService.look_up(user);
        if(return_user != null){
            change_password(user.getPassword());
        }
        return null;
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경 - 수정 필요
    @ResponseBody
    @RequestMapping(value = "/changing/password", method = RequestMethod.POST)
    public ResponseEntity change_password(@RequestBody String new_password){
        return new ResponseEntity(userService.change_password(new_password), HttpStatus.OK);
    }
}
