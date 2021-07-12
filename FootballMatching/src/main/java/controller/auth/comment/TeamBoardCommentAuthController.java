package controller.auth.comment;

import annotation.UserAuth;
import domain.Comment;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.auth.comment.TeamBoardCommentAuthService;

@Controller
@RequestMapping(value = "/team-board-comment")
@UserAuth
public class TeamBoardCommentAuthController {

    @Autowired
    private TeamBoardCommentAuthService teamBoardCommentAuthService;

    //댓글 생성
    @ResponseBody
    @RequestMapping(value = "/registeration", method = RequestMethod.POST)
    @ApiOperation(value ="댓글 생성", notes = "게시글 번호, 내용을 입력합니다.")
    public ResponseEntity register(@RequestHeader(value = "Authorization") String token, @RequestBody Comment comment){
        teamBoardCommentAuthService.register(token, comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    //댓글 수정
    @ResponseBody
    @RequestMapping(value = "/modification", method = RequestMethod.PATCH)
    @ApiOperation(value = "댓글 수정", notes = "게시물 번호, 내용을 입력해야합니다.")
    public ResponseEntity modification(@RequestHeader(value = "Authorization") String token,
                                       @RequestBody Comment comment){
        teamBoardCommentAuthService.modification(token, comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    //댓글 삭제



}
