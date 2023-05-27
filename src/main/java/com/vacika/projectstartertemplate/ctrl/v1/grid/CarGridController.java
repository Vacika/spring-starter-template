package com.vacika.projectstartertemplate.ctrl.v1.grid;

import com.turkraft.springfilter.boot.Filter;
import com.vacika.projectstartertemplate.ctrl.base.BaseGridControllerImpl;
import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.dto.base.request.GridRequest;
import com.vacika.projectstartertemplate.dto.response.CarResponse;
import com.vacika.projectstartertemplate.mapper.CarMapper;
import com.vacika.projectstartertemplate.service.grid.CarGridService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/grid/cars")
@Tag(name = "Car GRID Controller")
public class CarGridController extends BaseGridControllerImpl<CarResponse, Car> {
    public CarGridController(CarGridService gridService, CarMapper mapper) {
        super(gridService, mapper::toResponse);
    }

    @Operation(summary = "Filter cars (paginated)")
    @GetMapping
    public ResponseEntity<Object> getAllPaginated(@Filter Specification<Car> filter, GridRequest gridRequest) {
        return super.findAll(filter, gridRequest);
    }
}
