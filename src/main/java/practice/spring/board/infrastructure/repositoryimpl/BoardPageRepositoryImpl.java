package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.domain.model.boardpageable.BoardPageable;
import practice.spring.board.infrastructure.persistence.BoardCommentJpaRepository;

import javax.validation.constraints.Positive;
import java.util.List;

@Component
public class BoardPageRepositoryImpl implements BoardPageRepository {
    private final BoardCommentJpaRepository boardCommentJpaRepository;

    public BoardPageRepositoryImpl(BoardCommentJpaRepository boardCommentJpaRepository) {
        this.boardCommentJpaRepository = boardCommentJpaRepository;
    }

    @Override
    public BoardPage findBoardPage(int pageNum, int size) {
        var boardPageable = BoardPageable.of(pageNum, size);
        List<BoardComment> boardCommentList = boardCommentJpaRepository.findAll(boardPageable).getContent();
        return new BoardPage(boardCommentList, boardPageable, size);
    }

    @Override
    public BoardPage findLatestBoardPage(@Positive int size) {
        var totalNumber = boardCommentJpaRepository.count();
        var boardPageable = BoardPageable.of((int) totalNumber / size, size);
        List<BoardComment> boardCommentList = boardCommentJpaRepository.findAll(boardPageable).getContent();
        return new BoardPage(boardCommentList, boardPageable, size);
    }
}
