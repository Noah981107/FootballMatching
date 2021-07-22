package repository.non_auth;

import domain.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    String findId(String name); // 팀 id 찾기
    String findUserIdx(String idx); // 팀장 찾기
    String checkTeam(String idx); // 팀장으로 팀 id 찾기
    List<Team> teamList(); // 팀 전체 조회
    List<Team> findName(String teamName); // 팀 이름으로 팀 찾기
    List<Team> findLocal(int locationCode); // 지역별 팀 조회

}
