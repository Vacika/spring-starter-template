package com.vacika.projectstartertemplate.mapper;

import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.dto.request.MutateCarRequest;
import com.vacika.projectstartertemplate.dto.response.CarResponse;
import com.vacika.projectstartertemplate.mapper.base.BaseAuditedEntityMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {BaseAuditedEntityMapper.class})
public interface CarMapper {

    public CarResponse toResponse(Car car);

    public Car toEntity(MutateCarRequest carRequest);
}
