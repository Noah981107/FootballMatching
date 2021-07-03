package controller.auth;

import annotation.TeamAuth;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.TeamAuthService;

@Controller
@RequestMapping("/team")
@TeamAuth
public class TeamAuthController {

    @Autowired
    private TeamAuthService teamAuthService;

    //팀 등록
    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void registration(@RequestHeader(value = "Authorization") String token, @RequestBody Team team){
        teamAuthService.registration(token, team);
    }

    //팀 내용 변경
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    public void modification(@RequestHeader(value = "Authorization") String token, @RequestBody Team team){
        teamAuthService.modification(token, team);
    }

    //팀 삭제
    @ResponseBody
    @RequestMapping(value = "/deletion", method = RequestMethod.DELETE)
    public void deletion(@RequestHeader(value = "Authorization") String token, @RequestParam(value ="team-name") String teamName){
        teamAuthService.deletion(token, teamName);
    }

    //회원이 등록한 팀 조회
    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity myTeam(@RequestHeader(value = "Authorization") String token){
        return new ResponseEntity(teamAuthService.myTeam(token), HttpStatus.OK);
    }


}

