package service.non_auth;

import domain.BusinessUsers;

public interface BusinessUserService {
    String checkId(String id); // id 중복 체크

    String checkPhoneNumber(String phoneNumber); // 전화번호 중복 체크

    int checkFieldName(String fieldName); // 구장 확인

    String signUp(BusinessUsers bUser); // 회원가입

    String signIn(BusinessUsers bUser); // 로그인

    String findId(String name, String phoneNumber, String fieldName); // id 찾기

    BusinessUsers lookUp(BusinessUsers bUser); // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악

    void changePassword(BusinessUsers bUser); // 비밀번호 찾기 - 비밀번호 변경
}
