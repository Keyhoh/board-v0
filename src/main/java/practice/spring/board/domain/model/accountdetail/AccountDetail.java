package practice.spring.board.domain.model.accountdetail;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Builder
public class AccountDetail implements UserDetails {
    private enum Role {
        USER,
    }

    @Getter(onMethod = @__(@Override))
    private String username;

    @Getter(onMethod = @__(@Override))
    private String password;

    @Builder.Default
    private Collection<Role> authorities = Collections.singleton(Role.USER);

    @Getter(onMethod = @__(@Override))
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(Arrays.deepToString(authorities.toArray()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
