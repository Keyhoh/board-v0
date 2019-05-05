package practice.spring.board.infrastructure.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.spring.board.infrastructure.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
