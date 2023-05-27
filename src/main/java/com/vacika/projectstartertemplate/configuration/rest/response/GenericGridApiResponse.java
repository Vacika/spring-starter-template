package com.vacika.projectstartertemplate.configuration.rest.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GenericGridApiResponse extends GenericApiResponse {
    private Long totalElements;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
}
