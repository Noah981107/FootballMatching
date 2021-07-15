package service.auth.comment;

import domain.Comment;

public interface PlayerBoardCommentAuthService {
    void register(Comment comment);
    void modification(Comment comment);
    void deletion(Comment comment);
}
