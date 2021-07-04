package serviceImpl.non_auth.board;

import domain.board.PlayerBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.board.PlayerBoardMapper;
import service.non_auth.board.PlayerBoardService;

import java.util.List;

@Service
public class PlayerBoardServiceImpl implements PlayerBoardService {

    @Autowired
    private PlayerBoardMapper playerBoardMapper;

    @Override
    public List<PlayerBoard> list() {
        return playerBoardMapper.list();
    }

    @Override
    public List<PlayerBoard> findName(String teamName) {
        return playerBoardMapper.findName(teamName);
    }

    @Override
    public List<PlayerBoard> findWriter(String writer) {
        return playerBoardMapper.findWriter(writer);
    }
}
