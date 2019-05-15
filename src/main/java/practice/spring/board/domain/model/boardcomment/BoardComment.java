package practice.spring.board.domain.model.boardcomment;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "BoardComments")
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;

    private String username;

    @Column(name = "text")
    private String comment;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime postAt;
}
