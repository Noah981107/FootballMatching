package serviceImpl.non_auth.board;

import domain.Board;
import exception.ErrorCode;
import exception.TeamBoardException;
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
    public List<Board> list() {
        List<Board> result = playerBoardMapper.list();
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        return result;
    }

    @Override
    public List<Board> findName(String teamName) {
        List<Board> result = playerBoardMapper.findName(teamName);
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        return result;
    }

    @Override
    public List<Board> findWriter(String writer) {
        List<Board> result = playerBoardMapper.findWriter(writer);
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        return result;
    }
}
