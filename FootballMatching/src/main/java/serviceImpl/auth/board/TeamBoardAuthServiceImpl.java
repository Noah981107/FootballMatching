package serviceImpl.auth.board;

import domain.Board;

import exception.ErrorCode;
import exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.board.TeamBoardAuthMapper;
import service.auth.board.TeamBoardAuthService;
import service.non_auth.TeamService;
import service.non_auth.UserService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TeamBoardAuthServiceImpl implements TeamBoardAuthService {

    @Autowired
    private TeamBoardAuthMapper teamBoardAuthMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Override
    public void write(Board board) throws Exception {
        String idx = userService.findIdx(jwtUtil.getId());
        String id = teamService.findId(board.getTeamName());
        if(id == teamService.checkTeam(idx)){
            board.setWriter(idx);
            board.setTeamName(id);
            board.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
            teamBoardAuthMapper.write(board);
        }
        else{
            throw new TeamException(ErrorCode.Not_Team_Registered_For);
        }

    }

    @Override
    public void modification(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        String id = teamService.findId(board.getTeamName());
        board.setTeamName(id);
        board.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamBoardAuthMapper.modification(board);
    }

    @Override
    public void deletion(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        String id = teamService.findId(board.getTeamName());
        board.setTeamName(id);
        teamBoardAuthMapper.deletion(board);
    }
}
