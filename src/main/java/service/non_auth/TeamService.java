package service.non_auth;

import domain.Team;

import java.util.List;

public interface TeamService {

    String findUserIdx(String idx); // 팀장 찾기
    String checkTeam(String user_idx); // 팀장으로 팀 id 찾기
    String findId(String name); // 팀 id 찾기
    List<Team> teamList(); // 팀 전체 조회
    List<Team> findName(String teamName) throws Exception; // 팀 이름으로 팀 찾기
    List<Team> findLocal(int locationCode); // 지역별 팀 조회

}
