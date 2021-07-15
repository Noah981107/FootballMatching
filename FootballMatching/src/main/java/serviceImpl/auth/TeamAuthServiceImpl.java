package serviceImpl.auth;

import domain.Team;
import exception.ErrorCode;
import exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.TeamAuthMapper;
import repository.non_auth.UserMapper;
import service.auth.TeamAuthService;
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
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    //팀 등록
    @Override
    public void registration(Team team) {
        String idx = userService.findIdx(jwtUtil.getId());
        team.setRepresentative(idx);
        team.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamAuthMapper.registration(team);
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

    //팀 삭제
    @Override
    public void deletion(String teamName) {
        String idx = userService.findIdx(jwtUtil.getId());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("idx", idx);
        map.put("teamName", teamName);
        teamAuthMapper.deletion(map);
    }

    // 팀 수정
    @Override
    public void modification(Team team) {
        String idx = userService.findIdx(jwtUtil.getId());
        team.setRepresentative(idx);
        team.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamAuthMapper.modification(team);
    }
}
