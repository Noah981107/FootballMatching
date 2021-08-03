package service.non_auth.comment;

import domain.Comment;

import java.util.List;

public interface PlayerBoardCommentService {
    List<Comment> comments(String id);
}
