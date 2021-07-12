package service.non_auth.comment;

import domain.Comment;

import java.util.List;

public interface TeamBoardCommentService {
    List<Comment> comments(String id);
}
