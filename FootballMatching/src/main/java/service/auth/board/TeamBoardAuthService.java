package service.auth.board;

import domain.board.TeamBoard;

public interface TeamBoardAuthService {
    void write(String token, TeamBoard teamBoard);
    void modification(String token, TeamBoard teamBoard);
    void deletion(String token, TeamBoard teamBoard);
}
