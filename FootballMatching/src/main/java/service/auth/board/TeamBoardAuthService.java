package service.auth.board;

import domain.board.TeamBoard;

public interface TeamBoardAuthService {
    void write(TeamBoard teamBoard);
    void modification(TeamBoard teamBoard);
    void deletion(TeamBoard teamBoard);
}
