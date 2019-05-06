package practice.spring.board.domain.model.accountdetail;

import java.util.Optional;

public interface AccountDetailRepository {
    Optional<AccountDetail> findByUsername(String username);
}
