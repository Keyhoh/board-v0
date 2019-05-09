package practice.spring.board.domain.model.boardpage;

public interface BoardPageRepository {
    BoardPage findBoardPage(int pageNum, int size);
    BoardPage findLatestBoardPage(int size);
}
