package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public record OwnerDto(int id,
                       @NotBlank(message = "Name should not be empty")
                       String name,
                       @PastOrPresent(message = "Date should not be in future")
                       LocalDate birthDate,
                       @Getter
                       List<CatDto> cats) {
}
