package practice.spring.board.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.spring.board.domain.model.accountdetail.AccountDetailRepository;

@Service
public class AccountDetailService implements UserDetailsService {
    private final AccountDetailRepository accountDetailRepository;

    public AccountDetailService(AccountDetailRepository accountDetailRepository) {
        this.accountDetailRepository = accountDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountDetailRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found."));
    }
}
