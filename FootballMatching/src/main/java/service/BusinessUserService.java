package service;

import domain.BusinessUsers;

public interface BusinessUserService {
    String checkId(String id); // id 중복 체크

    String checkPhoneNumber(String phoneNumber); // 전화번호 중복 체크

    int checkFieldName(String field_name); // 구장 확인

    String signUp(BusinessUsers b_user); // 회원가입

    String signIn(BusinessUsers b_user); // 로그인

    String findId(String name, String phoneNumber, String field_name); // id 찾기

    BusinessUsers lookUp(BusinessUsers b_user); // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악

    void changePassword(BusinessUsers b_user); // 비밀번호 찾기 - 비밀번호 변경
}
