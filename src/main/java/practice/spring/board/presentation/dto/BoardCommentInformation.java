package practice.spring.board.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import practice.spring.board.domain.model.boardcomment.BoardComment;

@Builder
@Getter
public class BoardCommentInformation {
    private int size;
    private BoardComment boardComment;
}
