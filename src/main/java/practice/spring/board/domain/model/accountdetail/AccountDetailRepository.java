package practice.spring.board.domain.model.accountdetail;

import practice.spring.board.domain.model.accountdetail.AccountDetail;

import java.util.Optional;

public interface AccountDetailRepository {
    Optional<AccountDetail> findByUsername(String username);
}
