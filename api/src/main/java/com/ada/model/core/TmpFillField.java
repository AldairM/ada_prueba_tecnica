package com.ada.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TMP_LLENAR_CAMPOS")
public class TmpFillField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Company Data
    private Long companyId;
    private String companyCode;
    private String companyName;
    private String companyDescription;

    // Application Data
    private Long applicationId;
    private String applicationCode;
    private String applicationName;
    private String applicationDescription;

    // Version Data
    private Long versionId;
    private String version;
    private String versionDescription;

    // VersionCompany Data
    private Long versionCompanyId;
    private String versionCompanyDescription;
}
