package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.accountdetail.AccountDetailRepository;
import practice.spring.board.infrastructure.entity.Account;
import practice.spring.board.infrastructure.persistence.AccountRepository;

import java.util.Optional;

@Component
public class AccountDetailRepositoryImpl implements AccountDetailRepository {
    private final AccountRepository accountRepository;

    public AccountDetailRepositoryImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountDetail> findByUsername(String username) {
        return accountRepository.findByUsername(username).map(this::toAccountDetail);
    }

    private AccountDetail toAccountDetail(Account account) {
        return new AccountDetail(account.getUsername(), account.getPassword(), account.isEnabled());
    }
}
