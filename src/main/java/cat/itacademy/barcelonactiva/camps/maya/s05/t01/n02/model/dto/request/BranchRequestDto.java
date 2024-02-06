package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequestDto {
    @NotBlank(message = "cannot be null")
    @Size(min=4, max=30, message = "must be between 4 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "cannot include special characters")
    private String name;

    @NotBlank(message = "cannot be null")
    private String country;
}