package serviceImpl.auth.board;

import domain.board.TeamBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.board.TeamBoardAuthMapper;
import repository.non_auth.TeamMapper;
import repository.non_auth.UserMapper;
import service.auth.TeamAuthService;
import service.auth.board.TeamBoardAuthService;
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
    private UserMapper userMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public void write(String token, TeamBoard teamBoard) {
        String writer = jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        teamBoard.setWriter(idx);
        String id = teamMapper.findId(teamBoard.getTeamName());
        teamBoard.setTeamName(id);
        teamBoard.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamBoardAuthMapper.write(teamBoard);
    }

    @Override
    public void modification(String token, TeamBoard teamBoard) {
        String writer = jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        teamBoard.setWriter(idx);
        String id = teamMapper.findId(teamBoard.getTeamName());
        teamBoard.setTeamName(id);
        teamBoard.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamBoardAuthMapper.modification(teamBoard);
    }

    @Override
    public void deletion(String token, TeamBoard teamBoard) {
        String writer= jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        teamBoard.setWriter(idx);
        String id = teamMapper.findId(teamBoard.getTeamName());
        teamBoard.setTeamName(id);
        teamBoardAuthMapper.deletion(teamBoard);
    }
}
