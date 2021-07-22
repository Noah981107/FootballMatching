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
import java.util.List;

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
    public List<Board> list() {
        String idx = userService.findIdx(jwtUtil.getId());
        return teamBoardAuthMapper.list(idx);
    }

    @Override
    public void write(Board board) throws Exception {
        String idx = userService.findIdx(jwtUtil.getId()); // 사용자의 idx 가져오기
        String id = teamService.findId(board.getTeamName()); // 팀이름을 검색하여 팀 id 가져오기
        if(id == null || !id.equals(teamService.checkTeam(idx))){
            throw new TeamException(ErrorCode.Not_Team_Registered_For);
        }
        else{
            board.setWriter(idx);
            board.setTeamName(id);
            board.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
            teamBoardAuthMapper.write(board);
        }

    }

    @Override
    public void modification(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        board.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamBoardAuthMapper.modification(board);
    }

    @Override
    public void deletion(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        teamBoardAuthMapper.deletion(board);
    }
}
