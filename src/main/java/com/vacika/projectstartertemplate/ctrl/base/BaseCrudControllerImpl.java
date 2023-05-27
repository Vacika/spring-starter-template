package com.vacika.projectstartertemplate.ctrl.base;

import com.vacika.projectstartertemplate.configuration.rest.handler.ResponseHandler;
import com.vacika.projectstartertemplate.domain.base.BaseEntity;
import com.vacika.projectstartertemplate.service.base.ManagementService;
import org.springframework.http.ResponseEntity;

public abstract class BaseCrudControllerImpl<REQUEST, RESPONSE, ENTITY extends BaseEntity, MNGMT_SERVICE extends ManagementService<REQUEST, RESPONSE, ENTITY>> implements BaseCrudController<REQUEST> {

    protected MNGMT_SERVICE service;

    public BaseCrudControllerImpl(MNGMT_SERVICE managementService) {
        this.service = managementService;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseHandler.success(service.getAll());
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        return ResponseHandler.success(service.getById(id));
    }

    @Override
    public ResponseEntity<Object> create(REQUEST request) {
        return ResponseHandler.created(service.create(request));
    }

    @Override
    public ResponseEntity<Object> update(Long id, REQUEST request) {
        return ResponseHandler.success(service.update(id, request));
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        service.delete(id);
        return ResponseHandler.deleted();
    }
}
