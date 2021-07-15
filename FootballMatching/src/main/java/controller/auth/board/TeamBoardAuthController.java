package controller.auth.board;

import annotation.UserAuth;
import domain.board.TeamBoard;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.board.TeamBoardAuthService;

@Controller
@RequestMapping(value = "/team-board")
public class TeamBoardAuthController {

    @Autowired
    private TeamBoardAuthService teamBoardAuthService;

    // 팀 모집 게시물 작성
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "팀 모집 게시물 작성", notes = "팀 이름, 내용을 입력합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity write(@RequestBody TeamBoard teamBoard){
        teamBoardAuthService.write(teamBoard);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 팀 모집 게시물 수정
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value = "팀 모집 게시물 수정", notes = "팀 이름, 내용을 입력합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity modification(@RequestBody TeamBoard teamBoard){
        teamBoardAuthService.modification(teamBoard);
        return new ResponseEntity(HttpStatus.OK);
    }
    // 팀 모집 게시물 삭제
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "팀 모집 게시물 삭제", notes = "팀 모집 게시물을 삭제합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity deletion(@RequestBody TeamBoard teamBoard){
        teamBoardAuthService.deletion(teamBoard);
        return new ResponseEntity(HttpStatus.OK);
    }
}
