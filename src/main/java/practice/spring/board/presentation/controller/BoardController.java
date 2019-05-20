package practice.spring.board.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import practice.spring.board.application.service.BoardService;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.presentation.dto.in.ReceiveBoardCommentDto;
import practice.spring.board.presentation.dto.out.SendBoardCommentDto;

import java.time.LocalDateTime;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // TODO: view から form で渡される予定
    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    public void postComment(@AuthenticationPrincipal AccountDetail accountDetail,
                                    @RequestBody ReceiveBoardCommentDto receiveBoardCommentDto) {
        var boardComment = BoardComment.builder()
                .username(accountDetail.getUsername())
                .comment(receiveBoardCommentDto.getComment())
                .postAt(LocalDateTime.now())
                .build();
        boardService.createComment(boardComment);
    }

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public SendBoardCommentDto readPage(@RequestParam int page, @RequestParam int size) {
        if (page < 0) {
            return SendBoardCommentDto.of(boardService.findLatestBoardPage(size));
        } else {
            return SendBoardCommentDto.of(boardService.findBoardPage(page, size));
        }
    }
}
