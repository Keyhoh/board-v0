package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.domain.model.boardpageable.BoardPageable;
import practice.spring.board.infrastructure.persistence.BoardCommentJpaRepository;

import javax.validation.constraints.Positive;

@Component
public class BoardPageRepositoryImpl implements BoardPageRepository {
    private final BoardCommentJpaRepository boardCommentJpaRepository;

    public BoardPageRepositoryImpl(BoardCommentJpaRepository boardCommentJpaRepository) {
        this.boardCommentJpaRepository = boardCommentJpaRepository;
    }

    @Override
    public BoardPage findBoardPage(int pageNum, int size) {
        var boardPageable = BoardPageable.of(pageNum, size);
        return BoardPage.of(boardCommentJpaRepository.findAll(boardPageable), boardPageable);
    }

    @Override
    public BoardPage findLatestBoardPage(@Positive int size) {
        var totalNumber = boardCommentJpaRepository.count();
        var boardPageable = BoardPageable.of((int) (totalNumber - 1)/ size, size);
        return BoardPage.of(boardCommentJpaRepository.findAll(boardPageable), boardPageable);
    }
}
