package controller.non_auth.board;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.board.PlayerBoardService;

@Controller
@RequestMapping(value ="/player-board")
public class PlayerBoardController {

    @Autowired
    private PlayerBoardService playerBoardService;

    //용병 모집 게시판 전체 보기
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "용병 모집 게시판 전체 보기", notes ="용병 모집 게시판 전체를 보여줍니다.")
    public ResponseEntity list() throws Exception{
        return new ResponseEntity(playerBoardService.list(), HttpStatus.OK);
    }

    //팀 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ApiOperation(value = "팀 이름으로 조회", notes = "팀 이름을 입력해야 합니다.")
    public ResponseEntity teamName(@RequestParam(value = "team-name") String teamName){
        return new ResponseEntity(playerBoardService.findName(teamName), HttpStatus.OK);
    }

    //작성자 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "작성자 이름으로 조회", notes = "작성자 이름을 입력해야 합니다.")
    public ResponseEntity writer(@RequestParam(value = "writer") String writer){
        return new ResponseEntity(playerBoardService.findWriter(writer), HttpStatus.OK);
    }
}
