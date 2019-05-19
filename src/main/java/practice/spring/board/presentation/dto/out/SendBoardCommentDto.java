package practice.spring.board.presentation.dto.out;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import practice.spring.board.domain.model.boardcomment.BoardComment;
import practice.spring.board.domain.model.boardpage.BoardPage;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class SendBoardCommentDto {
    private List<BoardComment> boardCommentList;
    private int totalPages;
    private long totalElements;
    private int page;
    private int size;

    public static SendBoardCommentDto of(BoardPage boardPage) {
        return builder()
                .boardCommentList(boardPage.getContent())
                .totalPages(boardPage.getTotalPages())
                .totalElements(boardPage.getTotalElements())
                .page(boardPage.getNumber())
                .size(boardPage.getSize())
                .build();
    }
}
