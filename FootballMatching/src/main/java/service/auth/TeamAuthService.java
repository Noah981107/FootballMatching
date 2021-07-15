package service.auth;

import domain.Team;

import java.util.List;

public interface TeamAuthService {
    void registration(Team team);
    List<Team> myTeam();
    void deletion(String teamName);
    void modification(Team team);
}
