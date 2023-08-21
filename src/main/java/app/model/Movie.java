package app.model;


import jakarta.persistence.*;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Title cannot be longer than 100 characters.")
    @Size(max=100)
    private String title;

    @NotNull(message = "Year must have four digits.")
    @Min(1900)
    @Max(3000)
    private Integer yearOfRelease;

    @NotBlank
    private String category;

    @NotBlank(message = "The description cannot be longer than 1000 characters.")
    @Size(max=1000)
    private String description;

    @NotNull(message = "The grade must be between 1.0 and 10.0.")
    @Min(1)
    @Max(10)
    private float grade;

}
