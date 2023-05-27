package com.vacika.projectstartertemplate.service.grid;

import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.dto.response.CarResponse;
import com.vacika.projectstartertemplate.repository.CarRepository;
import com.vacika.projectstartertemplate.service.base.AbstractGridServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CarGridServiceImpl extends AbstractGridServiceImpl<CarResponse, Car, CarRepository> implements CarGridService {
    public CarGridServiceImpl(CarRepository repository) {
        super(repository);
    }
}
