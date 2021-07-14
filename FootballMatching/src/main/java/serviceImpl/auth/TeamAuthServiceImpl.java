package serviceImpl.auth;

import domain.Team;
import exception.ErrorCode;
import exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.TeamAuthMapper;
import repository.non_auth.UserMapper;
import service.auth.TeamAuthService;
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
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    //팀 등록
    @Override
    public void registration(String token, Team team) {
        String id = jwtUtil.getId(token);
        String idx = userMapper.findIdx(id);
        team.setRepresentative(idx);
        team.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamAuthMapper.registration(team);
    }

    //내가 등록한 팀 조회
    @Override
    public List<Team> myTeam(String token) {
        String id = jwtUtil.getId(token);
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
    public void deletion(String token, String teamName) {
        String id = jwtUtil.getId(token);
        String idx = userMapper.findIdx(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("idx", idx);
        map.put("teamName", teamName);
        teamAuthMapper.deletion(map);
    }

    // 팀 수정
    @Override
    public void modification(String token, Team team) {
        String id = jwtUtil.getId(token);
        String idx = userMapper.findIdx(id);
        team.setRepresentative(idx);
        team.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamAuthMapper.modification(team);
    }
}
