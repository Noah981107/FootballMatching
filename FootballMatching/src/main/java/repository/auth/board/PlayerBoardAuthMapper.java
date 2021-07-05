package repository.auth.board;

import domain.board.PlayerBoard;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBoardAuthMapper {
    void write(PlayerBoard playerBoard);
    void modification(PlayerBoard playerBoard);
    void deletion(PlayerBoard playerBoard);
}
