package com.vacika.projectstartertemplate.ctrl.base;

import com.vacika.projectstartertemplate.configuration.rest.handler.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface BaseCrudController<REQUEST> {

    ResponseEntity<Object> getAll();

    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Object> create(REQUEST request);

    ResponseEntity<Object> update(Long id, REQUEST request);

    ResponseEntity<Object> delete(Long id);

    default ResponseEntity<Object> importData(MultipartFile file) {
        return ResponseHandler.notImplemented();
    }
}
