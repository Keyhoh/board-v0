package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.account.Account;
import practice.spring.board.domain.model.account.AccountRepository;
import practice.spring.board.infrastructure.persistence.AccountJpaRepository;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository {
    private AccountJpaRepository accountJpaRepository;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountJpaRepository.findByUsername(username);
    }
}
