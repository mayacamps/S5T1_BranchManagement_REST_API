package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "BranchDto Model information")
public class BranchDto {

    @Getter(AccessLevel.NONE)
    private final List<String> EUROPEAN_COUNTRIES = Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Branch Id", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "Branch name", example = "Human Resources")
    private String name;

    @Schema(description = "Branch country", example = "Spain")
    private String country;

    @Enumerated(EnumType.STRING)
    private Type type;

    public BranchDto(Integer id, String name, String country) {
        this.id = id;
        this.name = WordUtils.capitalize(name);
        this.country = WordUtils.capitalize(country);
        this.type = getType(country);
    }

    private Type getType(String country) {
        if (isEuropean(country)) {
            return Type.EU;
        }
        return Type.NON_EU;
    }

    private boolean isEuropean(String country) {
        return EUROPEAN_COUNTRIES.stream().anyMatch(country::equalsIgnoreCase);
    }


}
