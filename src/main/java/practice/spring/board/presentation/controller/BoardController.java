package practice.spring.board.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.comment.Comment;

@RestController
public class BoardController {
    @RequestMapping(value = "/postComment", method = RequestMethod.GET)
    public Comment postComment(@AuthenticationPrincipal AccountDetail accountDetail, @RequestParam String text) {
        return new Comment(accountDetail.getUsername(), text);
    }
}
