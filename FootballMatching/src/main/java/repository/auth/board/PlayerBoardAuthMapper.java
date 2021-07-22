package repository.auth.board;

import domain.Board;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBoardAuthMapper {
    void write(Board board);
    void modification(Board board);
    void deletion(Board board);
}
