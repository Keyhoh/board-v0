package practice.spring.board.domain.repository;

import practice.spring.board.domain.model.AccountDetail;

import java.util.Optional;

public interface AccountDetailRepository {
    Optional<AccountDetail> findByUsername(String username);
}
