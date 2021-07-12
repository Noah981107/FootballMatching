package controller.auth;

import annotation.UserAuth;
import domain.Users;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "회원 정보 수정", notes = "이름, 비밀번호, 전화번호 수정 가능합니다.")
    public ResponseEntity modification(@RequestHeader(value = "Authorization") String token,
                               @RequestBody Users user) throws Exception{
        userAuthService.modification(token, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    //자신의 정보 조회
    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ApiOperation(value = "자신의 정보 조회", notes = "자신의 정보를 조회합니다.")
    public ResponseEntity inquiry(@RequestHeader(value = "Authorization") String token){
        return new ResponseEntity(userAuthService.inquiry(token), HttpStatus.OK);
    }

    //회원 탈퇴
    @ResponseBody
    @RequestMapping(value="/withdraw", method = RequestMethod.PATCH)
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴를 합니다.")
    public void withdraw(@RequestHeader(value = "Authorization") String token){
        userAuthService.withdraw(token);
    }

}
