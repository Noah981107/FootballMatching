package serviceImpl.non_auth.board;

import domain.board.TeamBoard;
import exception.ErrorCode;
import exception.TeamBoardException;
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
    public List<TeamBoard> list() throws Exception {
        List<TeamBoard> result = teamBoardMapper.list();
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        else{
            return result;
        }
    }

    @Override
    public List<TeamBoard> findName(String teamName) {
        List<TeamBoard> result = teamBoardMapper.findName(teamName);
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        else{
            return result;
        }
    }

    @Override
    public List<TeamBoard> findWriter(String writer) {
        List<TeamBoard> result = teamBoardMapper.findWriter(writer);
        if(result.isEmpty()){
            throw new TeamBoardException(ErrorCode.Registered_Post_Is_Empty);
        }
        else{
            return result;
        }
    }
}
