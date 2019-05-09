package practice.spring.board.infrastructure.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import practice.spring.board.domain.model.boardcomment.BoardComment;

@Repository
public interface BoardCommentJpaRepository extends PagingAndSortingRepository<BoardComment, Integer> {
}
