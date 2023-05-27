package com.vacika.projectstartertemplate.dto.base.response;

import com.vacika.projectstartertemplate.dto.base.request.GridRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class GridResponse<ENTITY_RESPONSE extends BaseResponse> extends GridRequest {
    public List<ENTITY_RESPONSE> data;
    public Long totalElements = 0L;
    public int totalPages = 0;
}
