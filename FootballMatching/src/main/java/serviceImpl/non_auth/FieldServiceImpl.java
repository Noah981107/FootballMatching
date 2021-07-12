package serviceImpl.non_auth;

import domain.Fields;
import exception.ErrorCode;
import exception.FieldException;
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
        List<Fields> result = fieldMapper.searchField(locationCode);
        if(result.isEmpty()){
            throw new FieldException(ErrorCode.Registered_Field_Is_Empty);
        }
        else{
            return result;
        }
    }
}
