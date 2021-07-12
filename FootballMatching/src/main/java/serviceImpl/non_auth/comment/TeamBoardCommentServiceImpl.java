package serviceImpl.non_auth.comment;

import domain.Comment;
import exception.CommentException;
import exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.comment.TeamBoardCommentMapper;
import service.non_auth.comment.TeamBoardCommentService;

import java.util.List;

@Service
public class TeamBoardCommentServiceImpl implements TeamBoardCommentService {

    @Autowired
    private TeamBoardCommentMapper teamBoardCommentMapper;

    @Override
    public List<Comment> comments(String id) {
        List<Comment> result = teamBoardCommentMapper.comments(id);
        if(result.isEmpty()){
            throw new CommentException(ErrorCode.Registered_Comment_Is_Empty);
        }
        else{
            return result;
        }
    }
}
