package serviceImpl.non_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.BoardMapper;
import service.non_auth.BoardService;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<String> list() {
        return boardMapper.list();
    }
}
