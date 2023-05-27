package com.vacika.projectstartertemplate.dto.response;

import com.vacika.projectstartertemplate.domain.EngineType;
import com.vacika.projectstartertemplate.dto.base.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CarResponse extends BaseResponse {
    public String brand;
    public String model;
    public Integer manufacturingYear;
    public EngineType engineType;
}
