package controller.non_auth.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.board.TeamRecruitmentBoardService;

@Controller
@RequestMapping(value = "/team-recruitment-board")
public class TeamRecruitmentBoard {

    @Autowired
    private TeamRecruitmentBoardService teamRecruitmentBoardService;

    //팀 모집 게시판 전체 보기
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity teamRecruitmentList(){
        return new ResponseEntity(teamRecruitmentBoardService.teamRecruitmentList(), HttpStatus.OK);
    }

    //팀 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/finding-name", method = RequestMethod.GET)
    public ResponseEntity teamName(@RequestParam(value = "team-name") String teamName){
        return new ResponseEntity(teamRecruitmentBoardService.teamName(teamName), HttpStatus.OK);
    }

    //작성자 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "finding-writer", method = RequestMethod.GET)
    public ResponseEntity writer(@RequestParam(value = "writer") String writer){
        return new ResponseEntity(teamRecruitmentBoardService.writer(writer), HttpStatus.OK);
    }

}
