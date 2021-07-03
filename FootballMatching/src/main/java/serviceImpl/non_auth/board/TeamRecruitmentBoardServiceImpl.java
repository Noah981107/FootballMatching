package serviceImpl.non_auth.board;

import domain.board.TeamRecruitmentBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.non_auth.board.TeamRecruitmentBoardMapper;
import service.non_auth.board.TeamRecruitmentBoardService;

import java.util.List;

@Service
public class TeamRecruitmentBoardServiceImpl implements TeamRecruitmentBoardService {

    @Autowired
    private TeamRecruitmentBoardMapper teamRecruitmentBoardMapper;

    @Override
    public List<TeamRecruitmentBoard> teamRecruitmentList() {
        return teamRecruitmentBoardMapper.teamRecruitmentList();
    }

    @Override
    public List<TeamRecruitmentBoard> teamName(String teamName) {
        return teamRecruitmentBoardMapper.teamName(teamName);
    }

    @Override
    public List<TeamRecruitmentBoard> writer(String writer) {
        return teamRecruitmentBoardMapper.writer(writer);
    }
}
