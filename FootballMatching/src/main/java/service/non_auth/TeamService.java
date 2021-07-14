package service.non_auth;

import domain.Team;

import java.util.List;

public interface TeamService {

    String findId(String name); // 팀 id 찾기
    List<Team> teamList(); // 팀 전체 조회
    List<Team> findName(String teamName) throws Exception; // 팀 이름으로 팀 찾기
    List<Team> findLocal(int locationCode); // 지역별 팀 조회

}
