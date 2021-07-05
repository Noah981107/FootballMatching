package repository.non_auth;

import domain.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {
    Team findName(String teamName); // 팀 이름으로 팀 찾기
    List<Team> teamList(); // 팀 전체 조회
    List<Team> findLocal(int locationCode); // 지역별 팀 조회
    String findId(String name);
}
