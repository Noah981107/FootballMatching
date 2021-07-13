package service.auth.comment;

import domain.Comment;

public interface PlayerBoardCommentAuthService {
    void register(String token, Comment comment);
    void modification(String token, Comment comment);
    void deletion(String token, Comment comment);
}
