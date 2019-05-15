package practice.spring.board.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardCommentDto {
    private String comment;
}
