package practice.spring.board.infrastructure.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import practice.spring.board.infrastructure.vo.JpaBoardComment;

@Repository
public interface JpaBoardCommentRepository extends PagingAndSortingRepository<JpaBoardComment, Integer> {
}
