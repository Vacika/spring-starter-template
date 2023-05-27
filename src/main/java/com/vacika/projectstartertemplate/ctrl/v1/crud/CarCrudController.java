package com.vacika.projectstartertemplate.ctrl.v1.crud;

import com.vacika.projectstartertemplate.ctrl.base.BaseCrudControllerImpl;
import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.dto.request.MutateCarRequest;
import com.vacika.projectstartertemplate.dto.response.CarResponse;
import com.vacika.projectstartertemplate.service.management.CarManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
public class CarCrudController extends BaseCrudControllerImpl<MutateCarRequest, CarResponse, Car, CarManagementService> {
    public CarCrudController(CarManagementService service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MutateCarRequest request) {
        return super.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody MutateCarRequest request) {
        return super.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}
