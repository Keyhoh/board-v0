package practice.spring.board.presentation.dto.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReceiveBoardCommentDto {
    private String comment;
}
