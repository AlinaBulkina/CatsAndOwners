package entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Breed;
import models.Color;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "birthdate")
    @PastOrPresent(message = "Date should not be in future")
    private LocalDate birthDate;

    @Column(name = "breed")
    private Breed breed;

    @Column(name = "color")
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private Owner owner;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    private Set<Cat> friends;

    public Cat(String name, LocalDate birthDate, Breed breed, Color color, Owner owner, Set<Cat> friends) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.friends = friends;
    }

    public void addFriend(Cat friend) {
        Objects.requireNonNull(friend);
        friends.add(friend);
    }

    public void unfriend(Cat exFriend) {
        Objects.requireNonNull(exFriend);
        Set<Cat> set = new HashSet<>();
        for (Cat c : friends) {
            if (!c.equals(exFriend)) {
                set.add(c);
            }
        }
        friends = set;
    }


    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
