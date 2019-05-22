package practice.spring.board.infrastructure.vo;

import lombok.Getter;
import lombok.Setter;
import practice.spring.board.domain.model.boardcomment.BoardComment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "BoardComments")
public class JpaBoardComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String text;
    private LocalDateTime createdAt;

    public BoardComment toBoardComment() {
        return BoardComment.builder()
                .index(id)
                .username(username)
                .comment(text)
                .postAt(createdAt)
                .build();
    }
}
