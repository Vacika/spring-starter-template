package com.vacika.projectstartertemplate.mapper.base;

import com.vacika.projectstartertemplate.domain.base.AuditedEntity;
import com.vacika.projectstartertemplate.dto.base.response.AuditedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BaseAuditedEntityMapper {

    @Mapping(target = "createdDate", expression = "java(entity.getCreatedDate() != null ? entity.getCreatedDate() : null)")
    @Mapping(target = "modifiedDate", expression = "java(entity.getModifiedDate() != null ? entity.getModifiedDate() : null)")
    AuditedResponse map(AuditedEntity entity);
}
