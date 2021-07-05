package controller.auth.board;

import annotation.TeamBoardAuth;
import domain.board.TeamBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.board.TeamBoardAuthService;

@Controller
@TeamBoardAuth
@RequestMapping(value = "/team-board")
public class TeamBoardAuthController {

    @Autowired
    private TeamBoardAuthService teamBoardAuthService;

    // 팀 모집 게시물 작성
    @ResponseBody
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public void write(@RequestHeader(value = "Authorization") String token, @RequestBody TeamBoard teamBoard){
        teamBoardAuthService.write(token, teamBoard);
    }

    // 팀 모집 게시물 수정
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    public void modification(@RequestHeader(value = "Authorization") String token, @RequestBody TeamBoard teamBoard){
        teamBoardAuthService.modification(token, teamBoard);
    }
    // 팀 모집 게시물 삭제
    @ResponseBody
    @RequestMapping(value = "/deletion", method = RequestMethod.DELETE)
    public void deletion(@RequestHeader(value = "Authorization") String token, @RequestBody TeamBoard teamBoard){
        teamBoardAuthService.deletion(token, teamBoard);
    }
}
