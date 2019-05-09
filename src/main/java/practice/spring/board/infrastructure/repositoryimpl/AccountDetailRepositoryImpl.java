package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.accountdetail.AccountDetailRepository;
import practice.spring.board.domain.model.account.Account;
import practice.spring.board.infrastructure.persistence.AccountJpaRepository;

import java.util.Optional;

@Component
public class AccountDetailRepositoryImpl implements AccountDetailRepository {
    private final AccountJpaRepository accountJpaRepository;

    public AccountDetailRepositoryImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Optional<AccountDetail> findByUsername(String username) {
        return accountJpaRepository.findByUsername(username).map(this::toAccountDetail);
    }

    private AccountDetail toAccountDetail(Account account) {
        return AccountDetail.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .enabled(account.isEnabled())
                .build();
    }
}
