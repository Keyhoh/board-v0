package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;
import practice.spring.board.infrastructure.persistence.JpaBoardCommentRepository;
import practice.spring.board.infrastructure.vo.JpaBoardComment;

@Component
public class BoardCommentRepositoryImpl implements BoardCommentRepository {
    private final JpaBoardCommentRepository jpaBoardCommentRepository;

    public BoardCommentRepositoryImpl(JpaBoardCommentRepository jpaBoardCommentRepository) {
        this.jpaBoardCommentRepository = jpaBoardCommentRepository;
    }

    @Override
    public BoardComment save(BoardComment boardComment) {
        return toBoardComment(jpaBoardCommentRepository.save(toJpaBoardComment(boardComment)));
    }

    private JpaBoardComment toJpaBoardComment(BoardComment boardComment) {
        var jpaBoardComment = new JpaBoardComment();
        jpaBoardComment.setUsername(boardComment.getUsername());
        jpaBoardComment.setText(boardComment.getComment());
        jpaBoardComment.setCreatedAt(boardComment.getPostAt());
        return jpaBoardComment;
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
