package com.ada.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionCompanyDTO {
    private Long id;
    private String versionCompanyDescription;
    private Long companyId;
    private Long versionId;
}
