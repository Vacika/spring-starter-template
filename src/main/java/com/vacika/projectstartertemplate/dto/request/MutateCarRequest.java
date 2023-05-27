package com.vacika.projectstartertemplate.dto.request;

import com.vacika.projectstartertemplate.domain.EngineType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Validated
public class MutateCarRequest {
    @NotBlank(message = "Brand can not be left empty")
    public String brand;
    @NotBlank(message = "Model can not be left empty")
    public String model;
    @NotNull(message = "Manufacturing year must be set!")
    public Integer manufacturingYear;
    @NotNull(message = "Engine type must be set!")
    public EngineType engineType;
}
