package practice.spring.board.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
                .postAt(LocalDateTime.now())
                .build();
        when(boardCommentRepository.save(any(BoardComment.class))).thenAnswer(i -> i.getArgument(0));
        assertEquals(boardComment, boardService.createComment(boardComment));
    }

    @Test
    void findBoardPage() {
    }

    @Test
    void findLatestBoardPage() {
    }
}