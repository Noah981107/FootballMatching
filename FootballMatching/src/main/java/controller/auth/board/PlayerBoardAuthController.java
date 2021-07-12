package controller.auth.board;

import annotation.PlayerBoardAuth;
import domain.board.PlayerBoard;
import domain.board.TeamBoard;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.board.PlayerBoardAuthService;

@Controller
@PlayerBoardAuth
@RequestMapping(value = "/player-board")
public class PlayerBoardAuthController {

    @Autowired
    private PlayerBoardAuthService playerBoardAuthService;

    // 팀 모집 게시물 작성
    @ResponseBody
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    @ApiOperation(value = "용병 모집 게시물 작성", notes = "팀 이름, 내용을 입력합니다.")
    public void write(@RequestHeader(value = "Authorization") String token, @RequestBody PlayerBoard playerBoard){
        playerBoardAuthService.write(token, playerBoard);
    }

    // 팀 모집 게시물 수정
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    @ApiOperation(value = "용병 모집 게시물 수정", notes = "팀 이름, 내용을 입력합니다.")
    public void modification(@RequestHeader(value = "Authorization") String token, @RequestBody PlayerBoard playerBoard){
        playerBoardAuthService.modification(token, playerBoard);
    }
    // 팀 모집 게시물 삭제
    @ResponseBody
    @RequestMapping(value = "/deletion", method = RequestMethod.DELETE)
    @ApiOperation(value = "용병 모집 게시물 삭제", notes = "용병 모집 게시물을 삭제합니다.")
    public void deletion(@RequestHeader(value = "Authorization") String token, @RequestBody PlayerBoard playerBoard){
        playerBoardAuthService.deletion(token, playerBoard);
    }
}
