package repository.non_auth.board;

import domain.board.TeamRecruitmentBoard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRecruitmentBoardMapper {
    List<TeamRecruitmentBoard> teamRecruitmentList();
    List<TeamRecruitmentBoard> teamName(String teamName);
    List<TeamRecruitmentBoard> writer(String writer);
}
