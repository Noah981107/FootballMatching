package controller.non_auth;

import domain.Users;
import domain.validation.UserValidationGroups;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    @ApiOperation(value = "회원 가입", notes = "아이디, 비밀번호, 이름, 전화번호를 입력해야 합니다.")
    public ResponseEntity signUp(@RequestBody @Validated(UserValidationGroups.signUp.class) Users user) throws Exception{
        userService.signUp(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //일반 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ApiOperation(value = "로그인", notes = "아이디, 비밀번호를 입력해야 합니다.")
    public ResponseEntity signIn(@RequestBody @Validated(UserValidationGroups.signIn.class) Users user) throws Exception{
        return new ResponseEntity(userService.signIn(user),HttpStatus.CREATED);
    }

    //회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ApiOperation(value = "아이디 찾기", notes = "이름 , 전화번호를 입력해야합니다.")
    public ResponseEntity findId(@RequestBody @Validated(UserValidationGroups.signIn.class) Users user) throws Exception {
        return new ResponseEntity(userService.findId(user), HttpStatus.OK);
    }



    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ApiOperation(value = "회원 비밀번호 찾기 - 아이디, 이름, 전화번호 일치 확인", notes = "아이디, 이름, 전화번호를 입력해야합니다.")
    public ResponseEntity findPassword(@RequestBody @Validated(UserValidationGroups.findPassword.class) Users user) throws Exception{
        return new ResponseEntity(userService.lookUp(user), HttpStatus.OK) ;
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.PATCH)
    @ApiOperation(value = "회원 비밀번호 찾기 - 비밀번호 변경", notes = "회원 비밀번호 찾기 - id, 이름, 전화번호 일치 확인에서 나온 " +
            "JSON에서 PassWord만 변경해서 사용해야합니다.")
    public ResponseEntity changePassword(@RequestBody @Validated(UserValidationGroups.changePassword.class) Users user){
        userService.changePassword(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
