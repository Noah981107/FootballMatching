package serviceImpl.non_auth;

import domain.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.FieldMapper;
import service.non_auth.FieldService;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    //지역 코드별 구장 검색
    @Override
    public List<Fields> searchField(int locationCode) {
        int result = fieldMapper.checkLocationCode(locationCode);
        if(result > 0){
            return fieldMapper.searchField(locationCode);
        }
        else{
            return null;
        }
    }
}
