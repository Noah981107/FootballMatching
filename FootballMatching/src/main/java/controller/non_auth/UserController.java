package controller.non_auth;

import domain.Users;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "회원 가입", notes = "이름, 비밀번호, 전화번호를 입력해야 합니다.")
    public ResponseEntity signUp(@RequestBody Users user) throws Exception{
        userService.signUp(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    //일반 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ApiOperation(value = "로그인", notes = "아이디, 비밀번호를 입력해야 합니다.")
    public ResponseEntity signIn(@RequestBody Users user) throws Exception{
        return new ResponseEntity(userService.signIn(user),HttpStatus.OK);
    }

    //회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    @ApiOperation(value = "아이디 찾기", notes = "이름 , 전화번호를 입력해야합니다.")
    public ResponseEntity findId(@RequestParam(value = "name") String name ,
                                 @RequestParam(value = "phoneNumber") String phoneNumber) throws Exception {
        return new ResponseEntity(userService.findId(name, phoneNumber), HttpStatus.OK);
    }


    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/finding/password", method = RequestMethod.POST)
    @ApiOperation(value = "회원 비밀번호 찾기 - id, 이름, 전화번호 일치 확인", notes = "id, 이름, 전화번호를 입력해야합니다.")
    public ResponseEntity findPassword(@RequestBody Users user) throws Exception{
        return new ResponseEntity(userService.lookUp(user), HttpStatus.OK) ;
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/update/password", method = RequestMethod.PATCH)
    @ApiOperation(value = "회원 비밀번호 찾기 - 비밀번호 변경", notes = "회원 비밀번호 찾기 - id, 이름, 전화번호 일치 확인에서 나온 " +
            "JSON에서 PassWord만 변경해서 사용해야합니다.")
    public void changePassword(@RequestBody Users user){
        userService.changePassword(user);
    }

}
