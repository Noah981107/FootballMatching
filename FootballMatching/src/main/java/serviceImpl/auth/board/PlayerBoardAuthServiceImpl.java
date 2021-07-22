package serviceImpl.auth.board;

import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.board.PlayerBoardAuthMapper;
import service.auth.board.PlayerBoardAuthService;
import service.non_auth.TeamService;
import service.non_auth.UserService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PlayerBoardAuthServiceImpl implements PlayerBoardAuthService {

    @Autowired
    private PlayerBoardAuthMapper playerBoardAuthMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Override
    public void write(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        String id = teamService.findId(board.getTeamName());
        board.setTeamName(id);
        board.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        playerBoardAuthMapper.write(board);
    }

    @Override
    public void modification(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        String id = teamService.findId(board.getTeamName());
        board.setTeamName(id);
        board.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        playerBoardAuthMapper.modification(board);
    }

    @Override
    public void deletion(Board board) {
        String idx = userService.findIdx(jwtUtil.getId());
        board.setWriter(idx);
        String id = teamService.findId(board.getTeamName());
        board.setTeamName(id);
        playerBoardAuthMapper.deletion(board);
    }
}
