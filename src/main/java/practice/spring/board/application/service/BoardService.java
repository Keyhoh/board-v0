package practice.spring.board.application.service;

import org.springframework.stereotype.Service;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;
import practice.spring.board.presentation.dto.BoardCommentInformation;

import javax.validation.constraints.Positive;

/**
 * コメントの投稿・取得サービスを提供する
 */
@Service
public class BoardService {
    private final BoardCommentRepository boardCommentRepository;
    private final BoardPageRepository boardPageRepository;

    public BoardService(BoardCommentRepository boardCommentRepository, BoardPageRepository boardPageRepository) {
        this.boardCommentRepository = boardCommentRepository;
        this.boardPageRepository = boardPageRepository;
    }

    /**
     * 投稿されたコメントを登録する
     *
     * @param boardCommentInformation 投稿コメント情報
     * @return 登録コメント情報
     */
    public BoardComment createComment(BoardCommentInformation boardCommentInformation) {
        return boardCommentRepository.save(boardCommentInformation.getBoardComment());
    }

    public BoardPage readLatestBunchComment(@Positive int capacity) {
        return boardPageRepository.findLatestBoardPage(capacity);
    }
}
