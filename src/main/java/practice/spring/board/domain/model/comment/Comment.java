package practice.spring.board.domain.model.comment;

import lombok.Getter;

import java.time.LocalDateTime;

public class Comment {
    public Comment(String username, String text){
        this.username = username;
        this.text = text;
        this.postAt = LocalDateTime.now();
    }

    @Getter
    private String username;

    @Getter
    private String text;

    @Getter
    private LocalDateTime postAt;
}
