package com.vacika.projectstartertemplate.service.base;


import com.vacika.projectstartertemplate.domain.base.BaseEntity;

import java.util.List;

public interface ManagementService<REQUEST, RESPONSE, ENTITY extends BaseEntity> {

    RESPONSE create(REQUEST request);

    RESPONSE update(Long id, REQUEST request);

    void delete(Long id);

    RESPONSE getById(Long id);

    List<RESPONSE> getAll();

    ENTITY mapRequestToEntity(REQUEST request);

    RESPONSE mapEntityToResponse(ENTITY entity);
}