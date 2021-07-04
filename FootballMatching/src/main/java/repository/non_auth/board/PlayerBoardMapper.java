package repository.non_auth.board;

import domain.board.PlayerBoard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerBoardMapper {
    List<PlayerBoard> list();
    List<PlayerBoard> findName(String teamName);
    List<PlayerBoard> findWriter(String writer);
}
