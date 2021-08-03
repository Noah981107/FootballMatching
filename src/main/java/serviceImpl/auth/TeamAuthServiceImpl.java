package serviceImpl.auth;

import domain.Team;
import exception.ErrorCode;
import exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.auth.TeamAuthMapper;
import repository.non_auth.UserMapper;
import service.auth.TeamAuthService;
import service.non_auth.TeamService;
import service.non_auth.UserService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class TeamAuthServiceImpl implements TeamAuthService {

    @Autowired
    private TeamAuthMapper teamAuthMapper;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    //팀 등록
    @Override
    public void registration(Team team) throws Exception {
        String idx = userService.findIdx(jwtUtil.getId());
        if(idx.equals(teamService.findUserIdx(idx))){
            throw new TeamException(ErrorCode.Already_Registered_Team);
        }
        else{
            team.setRepresentative(idx);
            team.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()).toString());
            teamAuthMapper.registration(team);
        }
    }

    // 팀 수정
    @Transactional
    @Override
    public void modification(Team team) {
        String name = team.getName();
        String locationCode = Integer.toString(team.getLocationCode());
        String information = team.getInformation();
        String idx = userService.findIdx(jwtUtil.getId());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("user_idx", idx);
        map.put("modifiedDate",Timestamp.valueOf(LocalDateTime.now()).toString());
        if(name != null && !name.equals("string")){
            map.put("name", name);
            teamAuthMapper.updateName(map);
        }
        if(locationCode != null && !locationCode.equals("string")){
            map.put("locationCode", locationCode);
            teamAuthMapper.updateLocationCode(map);
        }
        if(information != null && !information.equals("string")){
            map.put("information", information);
            teamAuthMapper.updateInformation(map);
        }


    }

    //팀 삭제
    @Override
    public void deletion() {
        String idx = userService.findIdx(jwtUtil.getId());
        teamAuthMapper.deletion(idx);
    }

    //내가 등록한 팀 조회
    @Override
    public List<Team> myTeam() {
        String id = jwtUtil.getId();
        List<Team> result = teamAuthMapper.myTeam(id);
        if(result.isEmpty()){
            throw new TeamException(ErrorCode.Registered_Team_Is_Empty);
        }
        else{
            return result;
        }
    }
}
