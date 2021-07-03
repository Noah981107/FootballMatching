package service.auth;

import domain.Team;

import java.util.List;

public interface TeamAuthService {
    void registration(String token, Team team);
    List<Team> myTeam(String token);
    void deletion(String token, String teamName);
    void modification(String token, Team team);
}
