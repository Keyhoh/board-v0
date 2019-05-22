package practice.spring.board.domain.model.account;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Account{
    private String username;
    private String password;
    private boolean enabled;
}
