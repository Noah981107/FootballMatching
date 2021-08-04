package serviceImpl.non_auth.comment;

import domain.Comment;
import exception.CommentException;
import exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.comment.PlayerBoardCommentMapper;
import service.non_auth.comment.PlayerBoardCommentService;

import java.util.List;

@Service
public class PlayerBoardCommentServiceImpl implements PlayerBoardCommentService {

    @Autowired
    private PlayerBoardCommentMapper playerBoardCommentMapper;

    @Override
    public List<Comment> comments(String id) {
        List<Comment> result = playerBoardCommentMapper.comments(id);
        if(result.isEmpty()){
            throw new CommentException(ErrorCode.Registered_Comment_Is_Empty);
        }
        else{
            return result;
        }
    }
}
