package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.account.Account;
import practice.spring.board.domain.model.account.AccountRepository;
import practice.spring.board.infrastructure.persistence.JpaAccountRepository;
import practice.spring.board.infrastructure.vo.JpaAccount;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository {
    private JpaAccountRepository jpaAccountRepository;

    public AccountRepositoryImpl(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return jpaAccountRepository.findByUsername(username).map(JpaAccount::toAccount);
    }
}
