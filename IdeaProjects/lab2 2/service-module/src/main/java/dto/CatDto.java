package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public record CatDto(@Getter
                     int id,
                     @NotEmpty(message = "Name should not be empty") String name,
                     @PastOrPresent(message = "Date should not be in future") LocalDate birthDate,
                     @NotBlank(message = "Breed should not be empty") String breed,
                     @NotBlank(message = "Color should not be empty") String color,
                     int ownerId,
                     List<Integer> friendsId) {
}
