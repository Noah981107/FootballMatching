package controller.auth.board;

import annotation.UserAuth;
import domain.Board;
import domain.UserValidationGroups;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.auth.board.PlayerBoardAuthService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping(value = "/player-board")
public class PlayerBoardAuthController {

    @Autowired
    private PlayerBoardAuthService playerBoardAuthService;

    // 용병 모집 게시물 작성
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "용병 모집 게시물 작성", notes = "팀 이름, 내용을 입력합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity write(@RequestBody @Validated(UserValidationGroups.boardWrite.class) Board board) throws Exception{
        playerBoardAuthService.write(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 용병 모집 게시물 수정
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value = "용병 모집 게시물 수정", notes = "팀 이름, 내용을 입력합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity modification(@RequestBody @Validated(UserValidationGroups.boardModification.class) Board board){
        playerBoardAuthService.modification(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 용병 모집 게시물 삭제
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "용병 모집 게시물 삭제", notes = "용병 모집 게시물을 삭제합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity deletion(@RequestBody Board board){
        playerBoardAuthService.deletion(board);
        return new ResponseEntity(HttpStatus.OK);
    }

    //내가 등록한 용병 모집 게시물 보기
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ApiOperation(value = "등록한 팀 모집 게시물 조회", notes = "본인이 등록한 게시물을 조회합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity list(){
        return new ResponseEntity(playerBoardAuthService.list(), HttpStatus.OK);
    }
}
