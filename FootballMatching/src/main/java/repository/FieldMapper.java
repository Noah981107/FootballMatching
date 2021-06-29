package repository;

import domain.Fields;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldMapper {
    int check_locationCode(int location_code); // 지역 코드 검사
    List<Fields> search_field(int location_code); // 지역 코드에 따른 구장 검색
}
