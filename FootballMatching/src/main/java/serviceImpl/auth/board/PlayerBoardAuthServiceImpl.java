package serviceImpl.auth.board;

import domain.board.PlayerBoard;
import domain.board.TeamBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.board.PlayerBoardAuthMapper;
import repository.non_auth.TeamMapper;
import repository.non_auth.UserMapper;
import service.auth.board.PlayerBoardAuthService;
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
    private UserMapper userMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public void write(String token, PlayerBoard playerBoard) {
        String writer = jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        playerBoard.setWriter(idx);
        String id = teamMapper.findId(playerBoard.getTeamName());
        playerBoard.setTeamName(id);
        playerBoard.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        playerBoardAuthMapper.write(playerBoard);
    }

    @Override
    public void modification(String token, PlayerBoard playerBoard) {
        String writer = jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        playerBoard.setWriter(idx);
        String id = teamMapper.findId(playerBoard.getTeamName());
        playerBoard.setTeamName(id);
        playerBoard.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        playerBoardAuthMapper.modification(playerBoard);
    }

    @Override
    public void deletion(String token, PlayerBoard playerBoard) {
        String writer= jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        playerBoard.setWriter(idx);
        String id = teamMapper.findId(playerBoard.getTeamName());
        playerBoard.setTeamName(id);
        playerBoardAuthMapper.deletion(playerBoard);
    }
}
