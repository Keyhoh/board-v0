package practice.spring.board.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.spring.board.infrastructure.vo.JpaAccount;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends CrudRepository<JpaAccount, String> {
    Optional<JpaAccount> findByUsername(String username);
}
