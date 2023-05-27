package com.vacika.projectstartertemplate.service.management;

import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.dto.request.MutateCarRequest;
import com.vacika.projectstartertemplate.dto.response.CarResponse;
import com.vacika.projectstartertemplate.mapper.CarMapper;
import com.vacika.projectstartertemplate.service.base.ManagementServiceImpl;
import com.vacika.projectstartertemplate.service.crud.CarCrudService;
import org.springframework.stereotype.Service;

@Service
public class CarManagementService extends ManagementServiceImpl<MutateCarRequest, CarResponse, Car> {
    public CarManagementService(CarCrudService crudService, CarMapper carMapper) {
        super(crudService, carMapper::toEntity, carMapper::toResponse);
    }

    // Business logic goes here, override some of 'ManagementServiceImpl' methods if needed.
}
