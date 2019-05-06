package practice.spring.board.domain.model.comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> save(Comment comment);
    Optional<BunchComments> findBunchComments(int bunchCapacity, int pageNum);
}
