package practice.spring.board.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.spring.board.application.service.BoardService;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpage.BoardPage;
import practice.spring.board.presentation.dto.BoardCommentInformation;

import java.time.LocalDateTime;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // TODO: view から form で渡される予定
    @RequestMapping(value = "/postComment", method = RequestMethod.GET)
    public BoardComment postComment(@AuthenticationPrincipal AccountDetail accountDetail, @RequestParam String text) {
        var comment = BoardComment.builder()
                .username(accountDetail.getUsername())
                .text(text)
                .postAt(LocalDateTime.now())
                .build();
        var postCommentInformation = BoardCommentInformation.builder()
                .boardComment(comment)
                .size(50)
                .build();
        return boardService.createComment(postCommentInformation);
    }

    @RequestMapping(value = "/getPage")
    public BoardPage readPage(@RequestParam int pageNum, @RequestParam int size) {
        return boardService.findBoardPage(pageNum, size);
    }

    @RequestMapping(value = "/getLatestPage", method = RequestMethod.GET)
    public BoardPage readLatestPage(@RequestParam int size) {
        return boardService.findLatestBoardPage(size);
    }
}
