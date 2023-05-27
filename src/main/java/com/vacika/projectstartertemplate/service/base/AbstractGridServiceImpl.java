package com.vacika.projectstartertemplate.service.base;

import com.vacika.projectstartertemplate.domain.base.BaseEntity;
import com.vacika.projectstartertemplate.dto.base.request.GridRequest;
import com.vacika.projectstartertemplate.dto.base.response.BaseResponse;
import com.vacika.projectstartertemplate.dto.base.response.GridResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.function.Function;

@AllArgsConstructor
public class AbstractGridServiceImpl<RESPONSE extends BaseResponse, ENTITY extends BaseEntity, REPOSITORY extends JpaSpecificationExecutor<ENTITY> & JpaRepository<ENTITY, Long>>
        implements AbstractGridService<RESPONSE, ENTITY> {
    protected REPOSITORY repository;

    @Override
    public GridResponse<RESPONSE> getAll(final GridRequest gridRequest, final Function<ENTITY, RESPONSE> mappingFunction) {
        Sort sort = getSort(gridRequest);
        Pageable pageable = PageRequest.of(gridRequest.page - 1, gridRequest.pageSize, sort);
        var page = repository.findAll(pageable);
        return toGridResponse(mappingFunction, page);
    }

    @Override
    public GridResponse<RESPONSE> getAll(final Specification<ENTITY> filterSpecification, final GridRequest gridRequest, final Function<ENTITY, RESPONSE> mappingFunction) {
        Sort sort = getSort(gridRequest);
        Pageable pageable = PageRequest.of(gridRequest.page - 1, gridRequest.pageSize, sort);
        var page = repository.findAll(filterSpecification, pageable);
        return toGridResponse(mappingFunction, page);
    }

    private GridResponse<RESPONSE> toGridResponse(final Function<ENTITY, RESPONSE> mappingFunction, final Page<ENTITY> page) {
        GridResponse<RESPONSE> gridResponse = new GridResponse<>();
        gridResponse.data = page.getContent().stream().map(mappingFunction).toList();
        gridResponse.totalElements = page.getTotalElements();
        gridResponse.page = page.getNumber() + 1;
        gridResponse.pageSize = page.getSize();
        gridResponse.totalPages = page.getTotalPages();
        return gridResponse;
    }

    private static Sort getSort(final GridRequest gridRequest) {
        var sort = getDefaultSort();
        if (gridRequest.sortDirection != null && gridRequest.sortProperty != null) {
            sort = Sort.by(gridRequest.sortProperty);
            sort = gridRequest.sortDirection == Sort.Direction.ASC ? sort.ascending() : sort.descending();
        }
        return sort;
    }

    private static Sort getDefaultSort() {
        return Sort.by("id", "createdDate").descending();
    }
}
