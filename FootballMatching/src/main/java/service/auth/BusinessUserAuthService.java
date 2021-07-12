package service.auth;

import domain.BusinessUsers;

public interface BusinessUserAuthService {
    void modification(String token, BusinessUsers bUser) throws Exception;
    BusinessUsers inquiry(String token);
    void withdraw(String token);
}
