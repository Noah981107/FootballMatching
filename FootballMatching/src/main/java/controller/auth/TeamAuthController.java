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
public class TeamAuthController {

    @Autowired
    private TeamAuthService teamAuthService;

    //팀 등록
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "팀 등록", notes = "팀 이름, 대표자, 지역코드, 정보를 입력합니다.")
    public ResponseEntity registration(@RequestHeader(value = "Authorization") String token,
                                       @RequestBody Team team){
        teamAuthService.registration(token, team);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //팀 내용 변경
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value = "팀 내용 변경", notes = "등록한 팀에 대한 내용을 변경합니다.")
    public ResponseEntity modification(@RequestHeader(value = "Authorization") String token,
                                       @RequestBody Team team){
        teamAuthService.modification(token, team);
        return new ResponseEntity(HttpStatus.OK);
    }

    //팀 삭제
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "팀 삭제", notes = "등록한 팀을 삭제합니다.")
    public ResponseEntity deletion(@RequestHeader(value = "Authorization") String token,
                                   @RequestParam(value ="team-name") String teamName){
        teamAuthService.deletion(token, teamName);
        return new ResponseEntity(HttpStatus.OK);
    }

    //회원이 등록한 팀 조회
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "내가 등록한 팀 조회", notes = "등록한 팀을 조회합니다.")
    public ResponseEntity myTeam(@RequestHeader(value = "Authorization") String token){
        return new ResponseEntity(teamAuthService.myTeam(token), HttpStatus.OK);
    }

}

