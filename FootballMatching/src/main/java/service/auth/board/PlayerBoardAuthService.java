package service.auth.board;

import domain.board.PlayerBoard;
import domain.board.TeamBoard;

public interface PlayerBoardAuthService {

    void write(String token, PlayerBoard playerBoard);
    void modification(String token, PlayerBoard playerBoard);
    void deletion(String token, PlayerBoard playerBoard);
}
