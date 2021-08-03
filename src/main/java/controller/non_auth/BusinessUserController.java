package controller.non_auth;

import domain.BusinessUsers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.non_auth.BusinessUserService;

// 예약 기능 없으니까 잠시 보류
@Controller
@RequestMapping(value = "/business-user")
public class BusinessUserController {

    @Autowired
    private BusinessUserService bUserService;

    // 사업자 회원 회원가입 API
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiOperation(value = "사업자 회원 회원가입", notes = "이름, 비밀번호, 전화번호, 구장 이름을 입력해야합니다.")
    public ResponseEntity signUp(@RequestBody BusinessUsers bUser) throws Exception{
        bUserService.signUp(bUser);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 사업자 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ApiOperation(value = "사업자 회원 로그인", notes = "아이디, 비밀번호를 입력해야합니다.")
    public ResponseEntity signIn(@RequestBody BusinessUsers bUser) throws Exception{
        return new ResponseEntity(bUserService.signIn(bUser), HttpStatus.CREATED);
    }

    // 사업자 회원 ID 찾기 API
    /*@ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    @ApiOperation(value = "사업자 회원 ID 찾기", notes = "이름, 전화번호, 구장 이름을 입력해야합니다.")
    public ResponseEntity findID(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "field-name") String fieldName) throws Exception{
        return new ResponseEntity(bUserService.findId(name,phoneNumber,fieldName), HttpStatus.OK);
    }*/

    // 사업자 회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/id", method = RequestMethod.POST)
    @ApiOperation(value = "사업자 회원 ID 찾기", notes = "이름, 전화번호, 구장 이름을 입력해야합니다.")
    public ResponseEntity findID(@RequestBody BusinessUsers bUser) throws Exception{
        return new ResponseEntity(bUserService.findId(bUser), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ApiOperation(value = "회원 비밀번호 찾기", notes = "id, 이름, 전화번호를 입력해야합니다.")
    public ResponseEntity findPassword(@RequestBody BusinessUsers bUser) throws Exception{
        return new ResponseEntity(bUserService.lookUp(bUser), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.PATCH)
    @ApiOperation(value = "비밀번호 변경", notes = "회원 비밀번호 찾기 - id, 이름, 전화번호 일치 여부 다음 과정입니다.")
    public ResponseEntity changePassword(@RequestBody BusinessUsers bUser){
        bUserService.changePassword(bUser);
        return new ResponseEntity(HttpStatus.OK);
    }
}
