package serviceImpl.non_auth.board;

import domain.board.TeamBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.board.TeamBoardMapper;
import service.non_auth.board.TeamBoardService;

import java.util.List;

@Service
public class TeamBoardServiceImpl implements TeamBoardService {

    @Autowired
    private TeamBoardMapper teamBoardMapper;

    @Override
    public List<TeamBoard> list() {
        return teamBoardMapper.list();
    }

    @Override
    public List<TeamBoard> findName(String teamName) {
        return teamBoardMapper.findName(teamName);
    }

    @Override
    public List<TeamBoard> findWriter(String writer) {
        return teamBoardMapper.findWriter(writer);
    }
}
