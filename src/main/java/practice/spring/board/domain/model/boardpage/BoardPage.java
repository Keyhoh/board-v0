package practice.spring.board.domain.model.boardpage;

import org.springframework.data.domain.PageImpl;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpageable.BoardPageable;

import java.util.List;

// 必要となるだろう interface がすべて PageImpl で実装されている。。。
public class BoardPage extends PageImpl<BoardComment> {

    // 一ページあたりのコメント数を制限するため、BoardPageable で実装
    public BoardPage(List<BoardComment> content, BoardPageable pageable, long total) {
        super(content, pageable, total);
    }
}
