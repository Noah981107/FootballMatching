package service.auth.comment;

import domain.Comment;

public interface TeamBoardCommentAuthService {
    void register(Comment comment);
    void modification(Comment comment);
    void deletion(Comment comment);
}
