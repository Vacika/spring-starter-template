package com.vacika.projectstartertemplate.service.crud;

import com.vacika.projectstartertemplate.domain.Car;
import com.vacika.projectstartertemplate.repository.CarRepository;
import com.vacika.projectstartertemplate.service.base.AbstractCrudServiceImpl;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
public class CarCrudCrudServiceImpl extends AbstractCrudServiceImpl<Car, CarRepository> implements CarCrudService {
    public CarCrudCrudServiceImpl(final CarRepository carRepository, final RedissonClient redissonClient) {
        super(carRepository, redissonClient, "cars");
    }

    @Override
    protected void updateEntityFields(Car oldEntity, Car newEntity) {
        oldEntity.setBrand(newEntity.brand);
        oldEntity.setModel(newEntity.model);
        oldEntity.setEngineType(newEntity.engineType);
        oldEntity.setManufacturingYear(newEntity.manufacturingYear);
        oldEntity.setStatus(newEntity.status);
    }
}
