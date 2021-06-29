package serviceImpl;

import domain.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FieldMapper;
import service.FieldService;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    //지역 코드별 구장 검색
    @Override
    public List<Fields> search_field(int location_code) {
        int result = fieldMapper.check_locationCode(location_code);
        if(result > 0){
            return fieldMapper.search_field(location_code);
        }
        else{
            return null;
        }
    }
}
