package com.vacika.projectstartertemplate.service.base;

import com.vacika.projectstartertemplate.domain.base.BaseEntity;
import com.vacika.projectstartertemplate.dto.base.request.GridRequest;
import com.vacika.projectstartertemplate.dto.base.response.BaseResponse;
import com.vacika.projectstartertemplate.dto.base.response.GridResponse;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Function;

public interface AbstractGridService<
        RESPONSE extends BaseResponse,
        ENTITY extends BaseEntity> {
    GridResponse<RESPONSE> getAll(GridRequest gridRequest, Function<ENTITY, RESPONSE> mappingFunction);

    GridResponse<RESPONSE> getAll(Specification<ENTITY> filterSpecification, GridRequest gridRequest, Function<ENTITY, RESPONSE> mappingFunction);
}
