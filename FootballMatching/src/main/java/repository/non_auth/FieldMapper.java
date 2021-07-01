package repository.non_auth;

import domain.Fields;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldMapper {
    int checkLocationCode(int location_code); // 지역 코드 검사
    List<Fields> searchField(int location_code); // 지역 코드에 따른 구장 검색
    int checkFieldName(String field_name); // 구장 이름 확인
}
