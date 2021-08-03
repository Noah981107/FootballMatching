package service.auth;

import domain.BusinessUsers;

public interface BusinessUserAuthService {
    void modification(BusinessUsers bUser) throws Exception;
    BusinessUsers inquiry();
    void withdraw();
}
