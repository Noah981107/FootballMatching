package controller.auth.board;

import annotation.UserAuth;
import domain.Board;
import domain.ValidationGroups;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity write(@RequestBody @Validated(ValidationGroups.boardWrite.class) Board board) throws Exception{
        teamBoardAuthService.write(board);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 팀 모집 게시물 수정
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value = "팀 모집 게시물 수정", notes = "팀 모집 게시물 번호, 내용을 입력합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity modification(@RequestBody @Validated(ValidationGroups.boardModification.class) Board board){
        teamBoardAuthService.modification(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 팀 모집 게시물 삭제
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "팀 모집 게시물 삭제", notes = "팀 모집 게시물 번호를 받아서 삭제합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity deletion(@RequestBody Board board){
        teamBoardAuthService.deletion(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    //내가 등록한 팀 모집 게시물 보기
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ApiOperation(value = "등록한 팀 모집 게시물 조회", notes = "본인이 등록한 게시물을 조회합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity list(){
        return new ResponseEntity(teamBoardAuthService.list(), HttpStatus.OK);
    }
}
