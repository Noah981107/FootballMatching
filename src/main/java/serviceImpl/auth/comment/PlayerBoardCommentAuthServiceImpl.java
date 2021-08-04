package serviceImpl.auth.comment;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.auth.comment.PlayerBoardCommentAuthMapper;
import service.auth.comment.PlayerBoardCommentAuthService;
import service.non_auth.UserService;
import util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PlayerBoardCommentAuthServiceImpl implements PlayerBoardCommentAuthService {

    @Autowired
    private PlayerBoardCommentAuthMapper playerBoardCommentAuthMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public void register(Comment comment) {
        String idx = userService.findIdx(jwtUtil.getId());
        comment.setWriter(idx);
        comment.setPostDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        playerBoardCommentAuthMapper.register(comment);
    }

    @Override
    public void modification(Comment comment) {
        String idx = userService.findIdx(jwtUtil.getId());
        comment.setWriter(idx);
        comment.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()).toString());
        playerBoardCommentAuthMapper.modification(comment);
    }

    @Override
    public void deletion(Comment comment) {
        String idx = userService.findIdx(jwtUtil.getId());
        comment.setWriter(idx);
        playerBoardCommentAuthMapper.deletion(comment);
    }
}
