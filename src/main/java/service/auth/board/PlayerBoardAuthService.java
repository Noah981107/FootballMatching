package service.auth.board;

import domain.Board;

import java.util.List;

public interface PlayerBoardAuthService {

    void write(Board board);
    void modification(Board board);
    void deletion(Board board);
    List<Board> list();
}
