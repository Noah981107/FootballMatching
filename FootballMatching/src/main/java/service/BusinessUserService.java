package service;

import domain.BusinessUsers;

public interface BusinessUserService {
    String check_id(String id); // id 중복 체크
    String check_phoneNumber(String phoneNumber); // 전화번호 중복 체크
    int check_fieldName(String field_name); // 구장 확인
    String sign_up(BusinessUsers b_user);
}
