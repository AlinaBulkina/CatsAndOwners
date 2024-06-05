package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Role;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name should not be empty")
    private String name;

    @Column(name = "password")
    @NotBlank(message = "Name should not be empty")
    private String password;

    @Column(name = "role")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public User(String name, String password, Role role, Owner owner) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.owner = owner;
    }

}
