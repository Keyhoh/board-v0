package practice.spring.board.domain.model.account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByUsername(String username);
}
