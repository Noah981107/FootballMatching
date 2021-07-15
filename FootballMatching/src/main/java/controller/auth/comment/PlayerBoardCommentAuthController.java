package controller.auth.comment;

import annotation.UserAuth;
import domain.Comment;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.comment.PlayerBoardCommentAuthService;

@Controller
@RequestMapping(value = "/player-board-comment")
public class PlayerBoardCommentAuthController {

    @Autowired
    private PlayerBoardCommentAuthService playerBoardCommentAuthService;

    //댓글 생성
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value ="댓글 생성", notes = "게시글 번호, 내용을 입력합니다.")
    public ResponseEntity register(@RequestHeader(value = "Authorization") String token,
                                   @RequestBody Comment comment){
        playerBoardCommentAuthService.register(token, comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    //댓글 수정
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value = "댓글 수정", notes = "게시물 번호, 내용을 입력해야합니다.")
    public ResponseEntity modification(@RequestHeader(value = "Authorization") String token,
                                       @RequestBody Comment comment){
        playerBoardCommentAuthService.modification(token, comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    //댓글 삭제
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
    public ResponseEntity deletion(@RequestHeader(value = "Authorization") String token,
                                   @RequestBody Comment comment){
        playerBoardCommentAuthService.deletion(token, comment);
        return new ResponseEntity(HttpStatus.OK);
    }
}
