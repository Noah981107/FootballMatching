package service;

import domain.Fields;

import java.util.List;

public interface FieldService {
    List<Fields> search_field(int location_code); // 지역 코드별 구장 검색
}
