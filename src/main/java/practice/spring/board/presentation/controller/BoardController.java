package practice.spring.board.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import practice.spring.board.application.service.BoardService;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.presentation.dto.ReceiveBoardCommentDto;
import practice.spring.board.presentation.dto.SendBoardCommentDto;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping(value = "/postComment")
    public void postComment(@AuthenticationPrincipal AccountDetail accountDetail,
                                    @RequestBody ReceiveBoardCommentDto receiveBoardCommentDto) {
        var boardComment = BoardComment.builder()
                .username(accountDetail.getUsername())
                .comment(receiveBoardCommentDto.getComment())
                .build();
        boardService.createComment(boardComment);
    }

    @GetMapping(value = "/getPage")
    public SendBoardCommentDto readPage(@RequestParam int page, @RequestParam int size) {
        if (page < 0) {
            return SendBoardCommentDto.of(boardService.findLatestBoardPage(size));
        } else {
            return SendBoardCommentDto.of(boardService.findBoardPage(page, size));
        }
    }
}
