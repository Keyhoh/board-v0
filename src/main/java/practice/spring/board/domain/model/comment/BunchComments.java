package practice.spring.board.domain.model.comment;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BunchComments {
    private enum Capacity {
        FIFTY(50),
        HANDED(100);

        Capacity(int value) {
            this.value = value;
        }

        @Getter
        private final int value;

        public static Capacity valueOf(int value) {
            return Arrays.stream(Capacity.values())
                    .filter(capacity -> capacity.getValue() == value)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    public BunchComments(int capacity, List<Comment> commentList) {
        this.capacity = Capacity.valueOf(capacity);
        var amount = Math.min(this.capacity.getValue(), commentList.size());
        this.comments = Collections.unmodifiableList(commentList.subList(0, amount));
    }

    @Getter
    private Capacity capacity;

    @Getter
    private List<Comment> comments;
}
