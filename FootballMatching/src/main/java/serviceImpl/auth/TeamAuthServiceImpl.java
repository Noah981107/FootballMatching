package serviceImpl.auth;

import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.TeamAuthMapper;
import repository.non_auth.UserMapper;
import service.auth.TeamAuthService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
}
