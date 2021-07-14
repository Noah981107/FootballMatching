package controller.auth.board;

import annotation.UserAuth;
import domain.board.PlayerBoard;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.board.PlayerBoardAuthService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@UserAuth
@RequestMapping(value = "/player-board")
public class PlayerBoardAuthController {

    @Autowired
    private PlayerBoardAuthService playerBoardAuthService;

    // 팀 모집 게시물 작성
    @ResponseBody
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    @ApiOperation(value = "용병 모집 게시물 작성", notes = "팀 이름, 내용을 입력합니다.")
    public ResponseEntity write(@RequestHeader(value = "Authorization") String token,
                                @RequestBody PlayerBoard playerBoard){
        playerBoardAuthService.write(token, playerBoard);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 팀 모집 게시물 수정
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    @ApiOperation(value = "용병 모집 게시물 수정", notes = "팀 이름, 내용을 입력합니다.")
    public ResponseEntity modification(@RequestHeader(value = "Authorization") String token,
                                       @RequestBody PlayerBoard playerBoard){
        playerBoardAuthService.modification(token, playerBoard);
        return new ResponseEntity(HttpStatus.OK);
    }
    // 팀 모집 게시물 삭제
    @ResponseBody
    @RequestMapping(value = "/deletion", method = RequestMethod.DELETE)
    @ApiOperation(value = "용병 모집 게시물 삭제", notes = "용병 모집 게시물을 삭제합니다.")
    public ResponseEntity deletion(@RequestHeader(value = "Authorization") String token,
                                   @RequestBody PlayerBoard playerBoard){
        playerBoardAuthService.deletion(token, playerBoard);
        return new ResponseEntity(HttpStatus.OK);
    }
}
