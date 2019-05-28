package practice.spring.board.domain.model.boardpageable;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 一ページあたりで取得できるコメント数を制限した pageable の実装クラス
 * size の制限がある以外は springframework.data.domain.Pageable と同じ
 */
public class BoardPageable implements Pageable, Serializable {

    private final int page;
    private final Size size;

    private BoardPageable(int page, int size) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        this.page = page;
        this.size = Size.valueOf(size);
    }

    public static BoardPageable of(int page, int size) {
        return new BoardPageable(page, size);
    }

    @Override
    public int getPageSize() {
        return size.getValue();
    }


    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public long getOffset() {
        return (long) page * (long) size.getValue();
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable next() {
        return new BoardPageable(getPageNumber() + 1, getPageSize());
    }


    /**
     * Returns the {@link Pageable} requesting the previous {@link Page}.
     *
     * @return
     */
    public Pageable previous() {
        return getPageNumber() == 0 ? this : new BoardPageable(getPageNumber() - 1, getPageSize());
    }

    @Override
    public Pageable first() {
        return new BoardPageable(0, getPageSize());
    }

    // 具体的に実装するとライブラリ依存してしまうので、unsorted で実装する
    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + page;
        result = prime * result + size.getValue();

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BoardPageable other = (BoardPageable) obj;
        return this.page == other.page && this.size == other.size;
    }

    private enum Size {
        TEN(10),
        FIFTY(50),
        HANDED(100);

        Size(int value) {
            this.value = value;
        }

        @Getter
        private final int value;

        public static Size valueOf(@Positive int value) {
            return Arrays.stream(Size.values())
                    .filter(size -> size.getValue() == value)
                    .findFirst()
                    .orElseThrow(()->new IllegalArgumentException("Unexpected argument: " + value));
        }
    }
}
