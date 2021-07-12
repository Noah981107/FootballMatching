package repository.non_auth;

import domain.Fields;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldMapper {
    List<Fields> searchField(int locationCode); // 지역 코드에 따른 구장 검색
    int checkFieldName(String fieldName); // 구장 이름 확인
}
