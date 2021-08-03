package controller.auth;

import annotation.BusinessUserAuth;
import domain.BusinessUsers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.BusinessUserAuthService;

@Controller
@RequestMapping(value = "/business-user")
public class BusinessUserAuthController {

    @Autowired
    private BusinessUserAuthService bUserAuthService;

    // 사업자 회원 정보 수정 - 비밀번호, 이름 , 전화번호 수정 가능
    @BusinessUserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value ="사업자 회원 정보 수정", notes = "이름, 비밀번호, 전화번호 수정", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity modification(@RequestBody BusinessUsers bUser) throws Exception{
       bUserAuthService.modification(bUser);
       return new ResponseEntity(HttpStatus.OK);
    }

    //자신의 정보 조회
    @BusinessUserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "자신의 정보 조회", notes = "자신의 정보를 조회합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity inquiry(){
        return new ResponseEntity(bUserAuthService.inquiry(), HttpStatus.OK);
    }

    //회원 탈퇴
    @BusinessUserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴를 합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity withdraw(){
        bUserAuthService.withdraw();
        return new ResponseEntity(HttpStatus.OK);
    }
}
