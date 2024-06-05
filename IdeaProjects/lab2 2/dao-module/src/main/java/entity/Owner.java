package entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name should not be empty")
    private String name;

    @Column(name = "birthDate")
    @PastOrPresent(message = "Date should not be in future")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cat> catList;

    public Owner(String name, LocalDate birthDate, List<Cat> cats) {
        this.name = name;
        this.birthDate = birthDate;
        this.catList = cats;
    }

    public void addCat(Cat cat) {
        Objects.requireNonNull(cat);
        if (!catList.contains(cat)) {
            catList.add(cat);
        }
    }

    public void removeCat(Cat cat) {
        Objects.requireNonNull(cat);
        catList.removeIf(k -> k.getId() == cat.getId());
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
