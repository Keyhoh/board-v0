package practice.spring.board.infrastructure.vo;

import lombok.Getter;
import lombok.Setter;
import practice.spring.board.domain.model.account.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class JpaAccount implements Serializable {
    @Id
    private String username;
    private String password;
    private boolean enabled;

    public Account toAccount(){
        return Account.builder()
                .username(username)
                .password(password)
                .enabled(enabled)
                .build();
    }
}
