package serviceImpl.auth.comment;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.comment.TeamBoardCommentAuthMapper;
import repository.non_auth.UserMapper;
import service.auth.comment.TeamBoardCommentAuthService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TeamBoardCommentAuthServiceImpl implements TeamBoardCommentAuthService {

    @Autowired
    private TeamBoardCommentAuthMapper teamBoardCommentAuthMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String token, Comment comment) {
        String writer = jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        comment.setWriter(idx);
        comment.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamBoardCommentAuthMapper.register(comment);
    }

    @Override
    public void modification(String token, Comment comment) {
        String writer = jwtUtil.getId(token);
        String idx = userMapper.findIdx(writer);
        comment.setWriter(idx);
        comment.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        teamBoardCommentAuthMapper.modification(comment);
    }
}
