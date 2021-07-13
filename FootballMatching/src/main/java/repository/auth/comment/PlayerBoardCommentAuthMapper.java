package repository.auth.comment;

import domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBoardCommentAuthMapper {
    void register(Comment comment);
    void modification(Comment comment);
    void deletion(Comment comment);
}
