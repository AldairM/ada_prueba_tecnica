package com.ada.config;

import com.ada.model.core.*;
import com.ada.repository.core.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final ApplicationRepository applicationRepository;
    private final VersionRepository versionRepository;
    private final VersionCompanyRepository versionCompanyRepository;
    private final TmpFillFieldRepository tmpFillFieldRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (companyRepository.count() > 0) {
            return;
        }

        // --- Sample Data 1 ---
        Company company1 = Company.builder()
                .code("COMP01")
                .name("Tech Solutions Inc")
                .description("Global technology provider")
                .build();
        companyRepository.save(company1);

        Application app1 = Application.builder()
                .code("APP01")
                .name("Inventory Manager")
                .description("Advanced inventory tracking system")
                .company(company1)
                .build();
        applicationRepository.save(app1);

        Version v1 = Version.builder()
                .version("1.0.0")
                .versionDescription("Initial Release")
                .application(app1)
                .build();
        versionRepository.save(v1);

        VersionCompany vc1 = VersionCompany.builder()
                .company(company1)
                .version(v1)
                .versionCompanyDescription("Standard license for Tech Solutions")
                .build();
        versionCompanyRepository.save(vc1);

        // Save to TMP_LLENAR_CAMPOS
        saveToTmp(company1, app1, v1, vc1);

        // --- Sample Data 2 ---
        Company company2 = Company.builder()
                .code("COMP02")
                .name("Innovative Retail")
                .description("Modern retail chain")
                .build();
        companyRepository.save(company2);

        Application app2 = Application.builder()
                .code("APP02")
                .name("Point of Sale")
                .description("Cloud-based POS system")
                .company(company2)
                .build();
        applicationRepository.save(app2);

        Version v2 = Version.builder()
                .version("2.1.0")
                .versionDescription("Performance update")
                .application(app2)
                .build();
        versionRepository.save(v2);

        Version v3 = Version.builder()
                .version("2.2.0-BETA")
                .versionDescription("New features beta")
                .application(app2)
                .build();
        versionRepository.save(v3);

        VersionCompany vc2 = VersionCompany.builder()
                .company(company2)
                .version(v2)
                .versionCompanyDescription("Production environment")
                .build();
        versionCompanyRepository.save(vc2);

        VersionCompany vc3 = VersionCompany.builder()
                .company(company2)
                .version(v3)
                .versionCompanyDescription("Staging environment")
                .build();
        versionCompanyRepository.save(vc3);

        // Save to TMP_LLENAR_CAMPOS
        saveToTmp(company2, app2, v2, vc2);
        saveToTmp(company2, app2, v3, vc3);
    }

    private void saveToTmp(Company c, Application a, Version v, VersionCompany vc) {
        TmpFillField tmp = TmpFillField.builder()
                .companyId(c.getId())
                .companyCode(c.getCode())
                .companyName(c.getName())
                .companyDescription(c.getDescription())
                .applicationId(a.getId())
                .applicationCode(a.getCode())
                .applicationName(a.getName())
                .applicationDescription(a.getDescription())
                .versionId(v.getId())
                .version(v.getVersion())
                .versionDescription(v.getVersionDescription())
                .versionCompanyId(vc.getId())
                .versionCompanyDescription(vc.getVersionCompanyDescription())
                .build();
        tmpFillFieldRepository.save(tmp);
    }
}
