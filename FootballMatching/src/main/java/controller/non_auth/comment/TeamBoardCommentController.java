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
@RequestMapping(value = "/team-board-comment")
public class TeamBoardCommentController {

    @Autowired
    private TeamBoardCommentService teamBoardCommentService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity comments(@RequestParam(value = "id") String id){
        return new ResponseEntity(teamBoardCommentService.comments(id), HttpStatus.OK);
    }

}
