package practice.spring.board.application.service;

import org.springframework.stereotype.Service;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;

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
     * @param boardComment 投稿コメント情報
     * @return 登録コメント情報
     */
    public BoardComment createComment(BoardComment boardComment) {
        return boardCommentRepository.save(boardComment);
    }

    /**
     * 指定のページを取得する
     *
     * @param pageNum ページ番号
     * @param size    1ページあたりのコメント数
     * @return 指定されたページ
     */
    public BoardPage findBoardPage(int pageNum, int size) {
        return boardPageRepository.findBoardPage(pageNum, size);
    }

    /**
     * 最後のページを取得する
     *
     * @param size 1ページあたりのコメント数
     * @return 最後のページ
     */
    public BoardPage findLatestBoardPage(@Positive int size) {
        return boardPageRepository.findLatestBoardPage(size);
    }
}
