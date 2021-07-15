package service.auth.board;

import domain.board.PlayerBoard;
import domain.board.TeamBoard;

public interface PlayerBoardAuthService {

    void write(PlayerBoard playerBoard);
    void modification(PlayerBoard playerBoard);
    void deletion(PlayerBoard playerBoard);
}
