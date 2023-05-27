package com.vacika.projectstartertemplate.domain;

import com.vacika.projectstartertemplate.domain.base.AuditedEntity;
import com.vacika.projectstartertemplate.domain.converters.EngineTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Setter;

@Setter
@Table(name = "cars")
@Entity
public class Car extends AuditedEntity {
    @Column(name = "brand", nullable = false, length = 128)
    public String brand;

    @Column(name = "model", nullable = false, length = 512)
    public String model;

    @Column(name = "manufacture_year")
    public Integer manufacturingYear;

    @Convert(converter = EngineTypeConverter.class)
    @Column(name = "engine_type", columnDefinition = "TINYINT")
    public EngineType engineType;
}
