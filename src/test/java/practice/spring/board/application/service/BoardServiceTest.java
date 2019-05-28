package practice.spring.board.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.domain.model.boardpageable.BoardPageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BoardServiceTest {

    @Mock
    private BoardCommentRepository boardCommentRepository;

    @Mock
    private BoardPageRepository boardPageRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setup() {
        initMocks(this);
    }

    @Test
    void createComment() {
        var boardComment = BoardComment.builder()
                .username("test user")
                .comment("test comment")
                .build();
        when(boardCommentRepository.save(any(BoardComment.class))).thenAnswer(i -> i.getArgument(0));
        var postedBoardComment = boardService.createComment(boardComment);
        assertEquals(postedBoardComment.getUsername(), boardComment.getUsername());
        assertEquals(postedBoardComment.getComment(), boardComment.getComment());
        assertTrue(postedBoardComment.getPostAt().isEqual(boardComment.getPostAt()));
    }

    @Test
    void findBoardPage() {
//        データの取得（ページ設定含む）はFWが行うので、ページサイズのチェックのみのテスト
        when(boardPageRepository.findBoardPage(anyInt(), anyInt())).thenAnswer(i ->
                getBoardPage(i.getArgument(0), i.getArgument(1), 150));
        assertThrows(IllegalArgumentException.class, () -> boardService.findBoardPage(0, 0));
        assertDoesNotThrow(() -> boardService.findBoardPage(0, 10));
        assertDoesNotThrow(() -> boardService.findBoardPage(0, 50));
        assertDoesNotThrow(() -> boardService.findBoardPage(0, 100));
    }

    @Test
    void findLatestBoardPage() {
    }

    private BoardPage getBoardPage(int pageNum, int size, int totalElements) {
        return new BoardPage(getBoardCommentList(totalElements), BoardPageable.of(pageNum, size), totalElements);
    }

    private List<BoardComment> getBoardCommentList(int size) {
        var boardCommentList = new ArrayList<BoardComment>();
        for (int i = 0; i < size; i++) {
            var boardComment = BoardComment.builder()
                    .username("test user")
                    .comment("comment" + i)
                    .build();
            boardCommentList.add(boardComment);
        }
        return boardCommentList;
    }
}