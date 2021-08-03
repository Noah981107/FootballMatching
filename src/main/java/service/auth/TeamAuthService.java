package service.auth;

import domain.Team;

import java.util.List;

public interface TeamAuthService {
    void registration(Team team) throws Exception;
    List<Team> myTeam();
    void deletion();
    void modification(Team team);
}
