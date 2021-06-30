package repository.auth;

import domain.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthMapper {
    void modification(Users user);
}
