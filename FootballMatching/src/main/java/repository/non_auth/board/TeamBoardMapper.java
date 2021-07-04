package repository.non_auth.board;

import domain.board.TeamBoard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamBoardMapper {
    List<TeamBoard> list();
    List<TeamBoard> findName(String teamName);
    List<TeamBoard> findWriter(String writer);
}
