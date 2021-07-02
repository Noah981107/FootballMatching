package controller.non_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.TeamService;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    //팀 전체 보기
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity teamList(){
        return new ResponseEntity(teamService.teamList(),HttpStatus.OK);
    }

    //팀 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/name-finding", method = RequestMethod.GET)
    public ResponseEntity findName(@RequestParam(value = "team-name") String teamName){
        return new ResponseEntity(teamService.findName(teamName), HttpStatus.OK);
    }

    //지역별 팀 조회
    @ResponseBody
    @RequestMapping(value = "/local-finding", method = RequestMethod.GET)
    public ResponseEntity findLocal(@RequestParam(value = "location-code") int locationCode){
        return new ResponseEntity(teamService.findLocal(locationCode), HttpStatus.OK);
    }
}
