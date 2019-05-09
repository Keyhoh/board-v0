package practice.spring.board.domain.model.boardcomment;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table(name = "BoardComments")
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String text;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime postAt;
}
