package service.auth;

import domain.Users;

public interface UserAuthService {
    String modification(String token, Users user);
}
