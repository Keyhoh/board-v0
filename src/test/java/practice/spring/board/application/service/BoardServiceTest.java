package practice.spring.board.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardcomment.BoardCommentRepository;
import practice.spring.board.domain.model.boardpage.BoardPageRepository;
import practice.spring.board.presentation.dto.BoardCommentInformation;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
    void setup(){
        initMocks(this);
    }

    @Test
    void createComment() {
        var boardComment = BoardComment.builder()
                .username("test user")
                .text("test comment")
                .postAt(LocalDateTime.now())
                .build();
        // BoardCommentInformation は内部的に size の制限があるが、
        // 外部入力のバリデーションチェックは controller で行うので、引数は正常であることを保証する
        var boardCommentInformation = BoardCommentInformation.builder()
                .size(10)
                .boardComment(boardComment)
                .build();
        when(boardCommentRepository.save(any(BoardComment.class))).thenAnswer(i -> i.getArgument(0));
        assertEquals(boardComment, boardService.createComment(boardCommentInformation));
    }

    @Test
    void readLatestBunchComment() {
    }

}