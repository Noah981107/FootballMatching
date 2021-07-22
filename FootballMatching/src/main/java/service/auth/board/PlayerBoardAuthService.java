package service.auth.board;

import domain.Board;

public interface PlayerBoardAuthService {

    void write(Board board);
    void modification(Board board);
    void deletion(Board board);
}
