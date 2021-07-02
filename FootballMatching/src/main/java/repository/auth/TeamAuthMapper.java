package repository.auth;

import domain.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAuthMapper {
    void registration(Team team);
}
