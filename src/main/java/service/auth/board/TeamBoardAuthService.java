package service.auth.board;

import domain.Board;

import java.util.List;


public interface TeamBoardAuthService {
    void write(Board board) throws Exception;
    void modification(Board board);
    void deletion(Board board);
    List<Board> list();
}
