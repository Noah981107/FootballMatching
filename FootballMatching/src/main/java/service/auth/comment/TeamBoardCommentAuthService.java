package service.auth.comment;

import domain.Comment;

public interface TeamBoardCommentAuthService {
    void register(String token, Comment comment);
    void modification(String token, Comment comment);
}
