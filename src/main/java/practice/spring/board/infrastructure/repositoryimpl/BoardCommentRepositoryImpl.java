package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;
import practice.spring.board.infrastructure.persistence.BoardCommentJpaRepository;

@Component
public class BoardCommentRepositoryImpl implements BoardCommentRepository {
    private final BoardCommentJpaRepository boardCommentJpaRepository;

    public BoardCommentRepositoryImpl(BoardCommentJpaRepository boardCommentJpaRepository) {
        this.boardCommentJpaRepository = boardCommentJpaRepository;
    }

    @Override
    public BoardComment save(BoardComment boardComment) {
        return boardCommentJpaRepository.save(boardComment);
    }
}
