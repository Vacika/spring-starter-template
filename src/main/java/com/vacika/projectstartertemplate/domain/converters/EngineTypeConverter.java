package com.vacika.projectstartertemplate.domain.converters;


import com.vacika.projectstartertemplate.domain.EngineType;
import jakarta.persistence.AttributeConverter;

public class EngineTypeConverter implements AttributeConverter<EngineType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EngineType attribute) {
        if (attribute == null)
            return null;

        return attribute.getId();
    }

    @Override
    public EngineType convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return EngineType.setById(dbData);
    }

}