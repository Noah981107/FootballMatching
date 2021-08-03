package repository.non_auth.comment;

import domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamBoardCommentMapper {
    List<Comment> comments(String id);
}
