package controller.auth;

import annotation.UserAuth;
import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.UserAuthService;

@Controller
@RequestMapping(value = "/user")
@UserAuth
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    //회원 정보 수정 - 비밀번호, 이름 , 전화번호 수정 가능
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    public String modification(@RequestHeader(value = "Authorization") String token, @RequestBody Users user){
        return userAuthService.modification(token, user);
    }

    //자신의 정보 조회
    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity inquiry(@RequestHeader(value = "Authorization") String token){
        return new ResponseEntity(userAuthService.inquiry(token), HttpStatus.OK);
    }

    //회원 탈퇴
    @ResponseBody
    @RequestMapping(value="/withdraw", method = RequestMethod.PATCH)
    public void withdraw(@RequestHeader(value = "Authorization") String token){
        userAuthService.withdraw(token);
    }

}
