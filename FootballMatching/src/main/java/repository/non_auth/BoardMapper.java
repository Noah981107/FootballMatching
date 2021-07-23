package repository.non_auth;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    List<String> list();
}
