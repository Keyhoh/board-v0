package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.domain.model.boardpageable.BoardPageable;
import practice.spring.board.infrastructure.persistence.JpaBoardCommentRepository;
import practice.spring.board.infrastructure.vo.JpaBoardComment;

import javax.validation.constraints.Positive;

@Component
public class BoardPageRepositoryImpl implements BoardPageRepository {
    private final JpaBoardCommentRepository jpaBoardCommentRepository;

    public BoardPageRepositoryImpl(JpaBoardCommentRepository jpaBoardCommentRepository) {
        this.jpaBoardCommentRepository = jpaBoardCommentRepository;
    }

    @Override
    public BoardPage findBoardPage(int pageNum, int size) {
        var boardPageable = BoardPageable.of(pageNum, size);
        return BoardPage.of(jpaBoardCommentRepository.findAll(boardPageable).map(this::toBoardComment), boardPageable);
    }

    @Override
    public BoardPage findLatestBoardPage(@Positive int size) {
        var totalNumber = jpaBoardCommentRepository.count();
        var boardPageable = BoardPageable.of((int) (totalNumber - 1) / size, size);
        return BoardPage.of(jpaBoardCommentRepository.findAll(boardPageable).map(this::toBoardComment), boardPageable);
    }

    private BoardComment toBoardComment(JpaBoardComment jpaBoardComment) {
        return BoardComment.builder()
                .index(jpaBoardComment.getId())
                .username(jpaBoardComment.getUsername())
                .comment(jpaBoardComment.getText())
                .postAt(jpaBoardComment.getCreatedAt())
                .build();
    }

}
