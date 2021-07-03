package service.non_auth.board;

import domain.board.TeamRecruitmentBoard;

import java.util.List;

public interface TeamRecruitmentBoardService {
    List<TeamRecruitmentBoard> teamRecruitmentList();
    List<TeamRecruitmentBoard> teamName(String teamName);
    List<TeamRecruitmentBoard> writer(String writer);
}
