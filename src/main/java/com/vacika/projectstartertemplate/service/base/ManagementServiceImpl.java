package com.vacika.projectstartertemplate.service.base;

import com.vacika.projectstartertemplate.domain.base.BaseEntity;

import java.util.List;
import java.util.function.Function;

public class ManagementServiceImpl<REQUEST, RESPONSE, ENTITY extends BaseEntity> implements ManagementService<REQUEST, RESPONSE, ENTITY> {

    protected final AbstractCrudService<ENTITY> crudService;
    protected final Function<REQUEST, ENTITY> requestToEntityMappingFunc;
    protected final Function<ENTITY, RESPONSE> entityToResponseMappingFunc;

    public ManagementServiceImpl(AbstractCrudService<ENTITY> crudService, Function<REQUEST, ENTITY> requestToEntityMappingFunc, Function<ENTITY, RESPONSE> entityToResponseMappingFunc) {
        this.crudService = crudService;
        this.requestToEntityMappingFunc = requestToEntityMappingFunc;
        this.entityToResponseMappingFunc = entityToResponseMappingFunc;
    }

    @Override
    public RESPONSE create(REQUEST request) {
        return mapEntityToResponse(crudService.create(mapRequestToEntity(request)));
    }

    @Override
    public RESPONSE update(Long id, REQUEST request) {
        return mapEntityToResponse(crudService.update(id, mapRequestToEntity(request)));
    }

    @Override
    public void delete(Long id) {
        crudService.softDelete(id);
    }

    @Override
    public RESPONSE getById(Long id) {
        return mapEntityToResponse(crudService.getById(id));
    }

    @Override
    public List<RESPONSE> getAll() {
        return crudService.getAll().stream().map(this::mapEntityToResponse).toList();
    }

    @Override
    public ENTITY mapRequestToEntity(REQUEST request) {
        return requestToEntityMappingFunc.apply(request);
    }

    @Override
    public RESPONSE mapEntityToResponse(ENTITY entity) {
        return entityToResponseMappingFunc.apply(entity);
    }
}
