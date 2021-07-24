package controller.non_auth;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.TeamService;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    //팀 전체 보기
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "등록된 팀 전체 보기", notes = "등록된 팀 전체를 조회합니다.")
    public ResponseEntity teamList(){
        return new ResponseEntity(teamService.teamList(),HttpStatus.OK);
    }

    //팀 이름으로 조회
    @ResponseBody
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ApiOperation(value = "팀 이름으로 조회", notes = "등록된 팀을 팀 이름으로 조회합니다.")
    public ResponseEntity findName(@RequestParam(value = "team-name", required = false) String teamName) throws Exception{
        return new ResponseEntity(teamService.findName(teamName), HttpStatus.OK);
    }

    //지역별 팀 조회
    @ResponseBody
    @RequestMapping(value = "/local", method = RequestMethod.GET)
    @ApiOperation(value = "지역별 팀 조회", notes = "지역별 팀을 전체 조회합니다.")
    public ResponseEntity findLocal(@RequestParam(value = "location-code") int locationCode){
        return new ResponseEntity(teamService.findLocal(locationCode), HttpStatus.OK);
    }
}
