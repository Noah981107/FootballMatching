package serviceImpl.non_auth.board;

import domain.board.PlayerBoard;
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
    public List<PlayerBoard> list() {
        List<PlayerBoard> result = playerBoardMapper.list();
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        return result;
    }

    @Override
    public List<PlayerBoard> findName(String teamName) {
        List<PlayerBoard> result = playerBoardMapper.findName(teamName);
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        return result;
    }

    @Override
    public List<PlayerBoard> findWriter(String writer) {
        List<PlayerBoard> result = playerBoardMapper.findWriter(writer);
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        return result;
    }
}
