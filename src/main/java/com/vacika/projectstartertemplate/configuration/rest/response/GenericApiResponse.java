package com.vacika.projectstartertemplate.configuration.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GenericApiResponse {
    private String msgTitle;
    private String msg;
    private int errCode;
    private Object data;
}
