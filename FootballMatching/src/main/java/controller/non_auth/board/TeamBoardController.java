package controller.non_auth.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.board.TeamBoardService;

@Controller
@RequestMapping(value = "/team-board")
public class TeamBoardController {

    @Autowired
    private TeamBoardService teamBoardService;

    //팀 모집 게시판 전체 보기
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(){
        return new ResponseEntity(teamBoardService.list(), HttpStatus.OK);
    }

    //팀 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/finding-name", method = RequestMethod.GET)
    public ResponseEntity teamName(@RequestParam(value = "team-name") String teamName){
        return new ResponseEntity(teamBoardService.findName(teamName), HttpStatus.OK);
    }

    //작성자 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/finding-writer", method = RequestMethod.GET)
    public ResponseEntity writer(@RequestParam(value = "writer") String writer){
        return new ResponseEntity(teamBoardService.findWriter(writer), HttpStatus.OK);
    }

}
