package service.non_auth.board;

import domain.board.PlayerBoard;

import java.util.List;

public interface PlayerBoardService {
    List<PlayerBoard> list() throws Exception;
    List<PlayerBoard> findName(String teamName);
    List<PlayerBoard> findWriter(String writer);
}
