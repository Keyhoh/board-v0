package practice.spring.board.infrastructure.repositoryimpl;

import org.springframework.stereotype.Component;
import practice.spring.board.domain.model.accountdetail.AccountDetail;
import practice.spring.board.domain.model.accountdetail.AccountDetailRepository;
import practice.spring.board.infrastructure.persistence.JpaAccountRepository;
import practice.spring.board.infrastructure.vo.JpaAccount;

import java.util.Optional;

@Component
public class AccountDetailRepositoryImpl implements AccountDetailRepository {
    private final JpaAccountRepository jpaAccountRepository;

    public AccountDetailRepositoryImpl(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public Optional<AccountDetail> findByUsername(String username) {
        return jpaAccountRepository.findByUsername(username).map(this::toAccountDetail);
    }

    private AccountDetail toAccountDetail(JpaAccount jpaAccount) {
        return AccountDetail.builder()
                .username(jpaAccount.getUsername())
                .password(jpaAccount.getPassword())
                .enabled(jpaAccount.isEnabled())
                .build();
    }
}
