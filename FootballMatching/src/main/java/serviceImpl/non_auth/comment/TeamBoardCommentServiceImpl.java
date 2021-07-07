package serviceImpl.non_auth.comment;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.board.TeamBoardMapper;
import repository.non_auth.comment.TeamBoardCommentMapper;
import service.non_auth.comment.TeamBoardCommentService;

import java.util.List;

@Service
public class TeamBoardCommentServiceImpl implements TeamBoardCommentService {

    @Autowired
    private TeamBoardCommentMapper teamBoardCommentMapper;

    @Override
    public List<Comment> list(String boardNumber) {
        return teamBoardCommentMapper.list(boardNumber);
    }
}
