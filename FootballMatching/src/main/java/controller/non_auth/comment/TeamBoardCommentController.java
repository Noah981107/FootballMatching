package controller.non_auth.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.comment.TeamBoardCommentService;

@Controller
@RequestMapping(value = "/team-board-Comment")
public class TeamBoardCommentController {

    @Autowired
    private TeamBoardCommentService teamBoardCommentService;

    //해당 게시물에 대한 댓글 전체 보기
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "board-number") String boardNumber){
        return new ResponseEntity(teamBoardCommentService.list(boardNumber), HttpStatus.OK);
    }

}
