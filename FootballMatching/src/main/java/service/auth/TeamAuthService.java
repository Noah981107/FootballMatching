package service.auth;

import domain.Team;

public interface TeamAuthService {
    void registration(String token, Team team);
}
