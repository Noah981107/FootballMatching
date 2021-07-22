package service.non_auth.board;

import domain.Board;

import java.util.List;

public interface PlayerBoardService {
    List<Board> list() throws Exception;
    List<Board> findName(String teamName);
    List<Board> findWriter(String writer);
}
