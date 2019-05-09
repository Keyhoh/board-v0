package practice.spring.board.domain.model.account;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    private String username;
    private String password;
    private boolean enabled;
}
