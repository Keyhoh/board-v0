package practice.spring.board.infrastructure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private String username;
    private String password;
    private boolean enabled;
}
