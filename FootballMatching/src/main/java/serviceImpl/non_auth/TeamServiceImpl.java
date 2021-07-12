package serviceImpl.non_auth;

import domain.Team;
import exception.ErrorCode;
import exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.FieldMapper;
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
        List<Team> result = teamMapper.teamList();
        if(result.isEmpty()){
            throw new TeamException(ErrorCode.Registered_Team_Is_Empty);
        }
        else{
            return result;
        }
    }

    //팀 이름으로 팀 조회
    @Override
    public Team findName(String teamName) throws Exception {
        Team return_team = teamMapper.findName(teamName);
        if(return_team == null){
            throw new TeamException(ErrorCode.Team_Is_Empty);
        }
        else{
            return return_team;
        }
    }
    
    //지역별 팀 조회
    @Override
    public List<Team> findLocal(int locationCode) {
        List<Team> result = teamMapper.findLocal(locationCode);
        if(result.isEmpty()){
            throw new TeamException(ErrorCode.Registered_Team_Is_Empty);
        }
        else{
            return result;
        }
    }
}
