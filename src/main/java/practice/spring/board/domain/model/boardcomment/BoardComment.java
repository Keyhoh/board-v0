package practice.spring.board.domain.model.boardcomment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardComment {
    private Integer index;
    private String username;
    private String comment;
    @Builder.Default
    private LocalDateTime postAt = LocalDateTime.now();
}
