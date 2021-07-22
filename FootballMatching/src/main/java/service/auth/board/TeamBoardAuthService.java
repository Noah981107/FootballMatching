package service.auth.board;

import domain.Board;


public interface TeamBoardAuthService {
    void write(Board board) throws Exception;
    void modification(Board board);
    void deletion(Board board);
}
