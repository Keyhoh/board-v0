package practice.spring.board.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.spring.board.domain.model.account.Account;

import java.util.Optional;

@Repository
public interface AccountJpaRepository extends CrudRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
