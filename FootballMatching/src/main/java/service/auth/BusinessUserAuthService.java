package service.auth;

import domain.BusinessUsers;

public interface BusinessUserAuthService {
    String modification(String token, BusinessUsers bUser);
    BusinessUsers inquiry(String token);
    void withdraw(String token);
}
