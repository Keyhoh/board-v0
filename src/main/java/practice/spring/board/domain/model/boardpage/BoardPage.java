package practice.spring.board.domain.model.boardpage;

import org.springframework.data.domain.PageImpl;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpageable.BoardPageable;

import java.util.List;

// 必要となるだろう interface がすべて PageImpl で実装されている。。。
public class BoardPage extends PageImpl<BoardComment> {

    public BoardPage(List<BoardComment> content, BoardPageable boardPageable, long total) {
        super(content, boardPageable, total);
    }

    // 使うメソッドは明示的に宣言

    @Override
    public List<BoardComment> getContent() {
        return super.getContent();
    }

    @Override
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @Override
    public int getNumber() {
        return super.getNumber();
    }
}
