package service;

import domain.BusinessUsers;

public interface BusinessUserService {
    String check_id(String id); // id 중복 체크

    String check_phoneNumber(String phoneNumber); // 전화번호 중복 체크

    int check_fieldName(String field_name); // 구장 확인

    String sign_up(BusinessUsers b_user); // 회원가입

    String sign_in(BusinessUsers b_user); // 로그인

    String find_id(String name, String phoneNumber, String field_name); // id 찾기

    BusinessUsers look_up(BusinessUsers b_user); // 비밀번호 찾기 - id, 전화번호, 이름, 구장 이름 일치 여부 파악

    void change_password(BusinessUsers b_user); // 비밀번호 찾기 - 비밀번호 변경
}
