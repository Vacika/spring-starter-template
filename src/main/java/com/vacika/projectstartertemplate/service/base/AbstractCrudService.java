package com.vacika.projectstartertemplate.service.base;

import java.util.List;

public interface AbstractCrudService<ENTITY> {
    List<ENTITY> getAll();

    ENTITY getById(final Long id);

    ENTITY create(final ENTITY entity);

    ENTITY update(final Long id, final ENTITY updatedEntity);

    void softDelete(final Long id);

    void softDelete(final List<ENTITY> entities);
}
