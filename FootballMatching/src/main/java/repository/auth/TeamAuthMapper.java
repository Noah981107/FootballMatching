package repository.auth;

import domain.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeamAuthMapper {
    void registration(Team team);
    List<Team> myTeam(String id);
    void deletion(Map map);
    void modification(Team team);
}
