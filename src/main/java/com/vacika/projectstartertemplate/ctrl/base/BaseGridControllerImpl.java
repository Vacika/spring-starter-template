package com.vacika.projectstartertemplate.ctrl.base;

import com.turkraft.springfilter.converter.FilterSpecification;
import com.vacika.projectstartertemplate.configuration.rest.handler.ResponseHandler;
import com.vacika.projectstartertemplate.domain.base.BaseEntity;
import com.vacika.projectstartertemplate.dto.base.request.GridRequest;
import com.vacika.projectstartertemplate.dto.base.response.BaseResponse;
import com.vacika.projectstartertemplate.dto.base.response.GridResponse;
import com.vacika.projectstartertemplate.service.base.AbstractGridService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

public abstract class BaseGridControllerImpl<
        RESPONSE extends BaseResponse,
        ENTITY extends BaseEntity> {

    private final Function<ENTITY, RESPONSE> mapEntityToEntityResponseFunction;
    protected AbstractGridService<RESPONSE, ENTITY> service;

    public BaseGridControllerImpl(AbstractGridService<RESPONSE, ENTITY> service, Function<ENTITY, RESPONSE> mapToResponseFunction) {
        this.service = service;
        this.mapEntityToEntityResponseFunction = mapToResponseFunction;
    }

    private GridResponse<RESPONSE> getAllNoFilter(GridRequest request) {
        return service.getAll(request, mapEntityToEntityResponseFunction);
    }

    private GridResponse<RESPONSE> getAllFiltered(Specification<ENTITY> filter, GridRequest gridRequest) {
        return service.getAll(filter, gridRequest, mapEntityToEntityResponseFunction);
    }

    protected ResponseEntity<Object> findAll(Specification<ENTITY> filter, GridRequest gridRequest) {
        GridResponse<RESPONSE> response;
        if (((FilterSpecification) filter).getFilter() == null) {
            response = getAllNoFilter(gridRequest);
        } else {
            response = getAllFiltered(filter, gridRequest);
        }
        return ResponseHandler.success(response);
    }
}
