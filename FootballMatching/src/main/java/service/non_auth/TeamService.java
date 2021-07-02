package service.non_auth;

import domain.Team;

import java.util.List;

public interface TeamService {

    Team findName(String teamName); // 팀 이름으로 팀 찾기
    List<Team> teamList(); // 팀 전체 조회
}
