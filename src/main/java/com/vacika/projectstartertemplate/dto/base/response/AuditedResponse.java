package com.vacika.projectstartertemplate.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditedResponse extends BaseResponse {
    public LocalDateTime createdDate;
    public LocalDateTime modifiedDate;
    public String createdBy;
    public String modifiedBy;
}
