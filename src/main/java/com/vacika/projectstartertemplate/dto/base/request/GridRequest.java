package com.vacika.projectstartertemplate.dto.base.request;

import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
public class GridRequest {
    public int page = 1;
    public int pageSize = 15;
    public Sort.Direction sortDirection;
    public String sortProperty;
}
