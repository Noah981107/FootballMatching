package controller.non_auth;

import domain.BusinessUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.non_auth.BusinessUserService;

@Controller
@RequestMapping(value = "/business-user")
public class BusinessUserController {

    @Autowired
    private BusinessUserService bUserService;

    // 사업자 회원 회원가입 API
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody BusinessUsers bUser){
        return new ResponseEntity(bUserService.signUp(bUser), HttpStatus.OK);
    }

    // 사업자 회원 로그인 API
    @ResponseBody
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody BusinessUsers bUser){
        return new ResponseEntity(bUserService.signIn(bUser), HttpStatus.OK);
    }

    // 사업자 회원 ID 찾기 API
    @ResponseBody
    @RequestMapping(value = "/finding/id", method = RequestMethod.GET)
    public ResponseEntity findID(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "field-name") String fieldName){
        return new ResponseEntity(bUserService.findId(name,phoneNumber,fieldName), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - id, 이름, 전화번호, 일치여부 확인
    @ResponseBody
    @RequestMapping(value = "/finding/password", method = RequestMethod.POST)
    public ResponseEntity findPassword(@RequestBody BusinessUsers bUser){
        return new ResponseEntity(bUserService.lookUp(bUser), HttpStatus.OK);
    }

    //회원 비밀번호 찾기 API - 비밀번호 변경
    @ResponseBody
    @RequestMapping(value = "/update/password", method = RequestMethod.PATCH)
    public void changePassword(@RequestBody BusinessUsers bUser){
        bUserService.changePassword(bUser);
    }
}
