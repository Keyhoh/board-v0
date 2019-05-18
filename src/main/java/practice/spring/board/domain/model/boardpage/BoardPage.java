package practice.spring.board.domain.model.boardpage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpageable.BoardPageable;

import java.util.List;

// 必要となるだろう interface がすべて PageImpl で実装されている。。。
public class BoardPage extends PageImpl<BoardComment> {

    private BoardPage(List<BoardComment> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public static BoardPage of(Page<BoardComment> page, BoardPageable pageable) {
        return new BoardPage(page.getContent(), pageable, page.getTotalElements());
    }

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
