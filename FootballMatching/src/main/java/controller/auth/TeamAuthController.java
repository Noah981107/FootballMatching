package controller.auth;

import annotation.UserAuth;
import domain.Team;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.TeamAuthService;

@Controller
@RequestMapping("/team")
@UserAuth
public class TeamAuthController {

    @Autowired
    private TeamAuthService teamAuthService;

    //팀 등록
    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ApiOperation(value = "팀 등록", notes = "팀 이름, 대표자, 지역코드, 정보를 입력합니다.")
    public void registration(@RequestHeader(value = "Authorization") String token, @RequestBody Team team){
        teamAuthService.registration(token, team);
    }

    //팀 내용 변경
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    @ApiOperation(value = "팀 내용 변경", notes = "등록한 팀에 대한 내용을 변경합니다.")
    public void modification(@RequestHeader(value = "Authorization") String token, @RequestBody Team team){
        teamAuthService.modification(token, team);
    }

    //팀 삭제
    @ResponseBody
    @RequestMapping(value = "/deletion", method = RequestMethod.DELETE)
    @ApiOperation(value = "팀 삭제", notes = "등록한 팀을 삭제합니다.")
    public void deletion(@RequestHeader(value = "Authorization") String token, @RequestParam(value ="team-name") String teamName){
        teamAuthService.deletion(token, teamName);
    }

    //회원이 등록한 팀 조회
    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ApiOperation(value = "내가 등록한 팀 조회", notes = "등록한 팀을 조회합니다.")
    public ResponseEntity myTeam(@RequestHeader(value = "Authorization") String token){
        return new ResponseEntity(teamAuthService.myTeam(token), HttpStatus.OK);
    }


}

