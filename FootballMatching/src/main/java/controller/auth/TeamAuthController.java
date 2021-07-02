package controller.auth;

import annotation.TeamAuth;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
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
    //팀 삭제
    //회원이 등록한 팀 조회
    
}

