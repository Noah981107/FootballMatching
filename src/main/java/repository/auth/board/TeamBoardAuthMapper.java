package repository.auth.board;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamBoardAuthMapper {
    void write(Board board);
    void modification(Board board);
    void deletion(Board board);
    List<Board> list(String idx);
}
