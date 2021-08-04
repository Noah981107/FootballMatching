package controller.auth.comment;

import annotation.UserAuth;
import domain.Comment;
import domain.ValidationGroups;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.auth.comment.TeamBoardCommentAuthService;

@Controller
@RequestMapping(value = "/team-board-comment")
public class TeamBoardCommentAuthController {

    @Autowired
    private TeamBoardCommentAuthService teamBoardCommentAuthService;

    //댓글 생성
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value ="댓글 생성", notes = "게시글 번호, 내용을 입력합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity register(@RequestBody @Validated(ValidationGroups.commentRegistration.class) Comment comment){
        teamBoardCommentAuthService.register(comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    //댓글 수정
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ApiOperation(value = "댓글 수정", notes = "게시물 번호, 댓글번호, 내용을 입력해야합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity modification(@RequestBody @Validated(ValidationGroups.commentModification.class) Comment comment){
        teamBoardCommentAuthService.modification(comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    //댓글 삭제
    @UserAuth
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "댓글 삭제", notes = "게시물 번호, 댓글번호을 입력해야 합니다.", authorizations = @Authorization(value = "Authorization"))
    public ResponseEntity deletion(@RequestBody @Validated(ValidationGroups.commentDeletion.class) Comment comment){
        teamBoardCommentAuthService.deletion(comment);
        return new ResponseEntity(HttpStatus.OK);
    }

}
