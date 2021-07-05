package repository.auth.board;

import domain.board.TeamBoard;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamBoardAuthMapper {
    void write(TeamBoard teamBoard);
    void modification(TeamBoard teamBoard);
    void deletion(TeamBoard teamBoard);
}
