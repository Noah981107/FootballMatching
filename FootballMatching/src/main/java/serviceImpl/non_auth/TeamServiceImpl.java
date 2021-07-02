package serviceImpl.non_auth;

import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.TeamMapper;
import service.non_auth.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    //팀 전체 조회
    @Override
    public List<Team> teamList() {
        return teamMapper.teamList();
    }

    //팀 이름으로 팀 조회
    @Override
    public Team findName(String teamName) {
        return teamMapper.findName(teamName);
    }
}
