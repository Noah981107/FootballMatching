package service.non_auth;

import domain.Fields;

import java.util.List;

public interface FieldService {
    List<Fields> searchField(int locationCode); // 지역 코드별 구장 검색
}
