package repository.non_auth.board;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamBoardMapper {
    List<Board> list();
    List<Board> findName(String teamName);
    List<Board> findWriter(String writer);
}
