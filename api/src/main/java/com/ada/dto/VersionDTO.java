package com.ada.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionDTO {
    private Long id;
    private String version;
    private String versionDescription;
    private Long applicationId;
}
