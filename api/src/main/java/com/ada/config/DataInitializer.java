package com.ada.config;

import com.ada.model.core.*;
import com.ada.repository.core.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

        private final CompanyRepository companyRepository;
        private final ApplicationRepository applicationRepository;
        private final VersionRepository versionRepository;
        private final VersionCompanyRepository versionCompanyRepository;
        private final TmpFillFieldRepository tmpFillFieldRepository;

        private static final char[] NAME_CHARS = "werfbhjiuytredfgyuiolkmnbvfrewsxfgyuikmnbvfrewwrtyuiokmnbvw sxcfghuioplknbvfdewazxcghuiopuytrewqsd f g kjv c x"
                        .replaceAll("\\s", "").toCharArray();
        private final Random random = new Random();

        @Override
        @Transactional
        public void run(String... args) throws Exception {
                if (companyRepository.count() > 0) {
                        return;
                }

                // --- Sample Data 1 ---
                String name1 = generateRandomCompanyName();
                Company company1 = Company.builder()
                                .code("COMP01")
                                .name(name1)
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
                                .versionCompanyDescription("Standard license for " + name1)
                                .build();
                versionCompanyRepository.save(vc1);

                // Save to TMP_LLENAR_CAMPOS
                saveToTmp(company1, app1, v1, vc1);

                // --- Sample Data 2 ---
                String name2 = generateRandomCompanyName();
                Company company2 = Company.builder()
                                .code("COMP02")
                                .name(name2)
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
                                .versionCompanyDescription("Production environment for " + name2)
                                .build();
                versionCompanyRepository.save(vc2);

                VersionCompany vc3 = VersionCompany.builder()
                                .company(company2)
                                .version(v3)
                                .versionCompanyDescription("Staging environment for " + name2)
                                .build();
                versionCompanyRepository.save(vc3);

                // Save to TMP_LLENAR_CAMPOS
                saveToTmp(company2, app2, v2, vc2);
                saveToTmp(company2, app2, v3, vc3);
        }

        private String generateRandomCompanyName() {
                int length = 5 + random.nextInt(6); // 5 to 10 chars
                String raw = IntStream.range(0, length)
                                .mapToObj(i -> String.valueOf(NAME_CHARS[random.nextInt(NAME_CHARS.length)]))
                                .collect(Collectors.joining());

                Pattern pattern = Pattern.compile("^([a-z])(.*)$");
                Matcher matcher = pattern.matcher(raw);

                if (matcher.find()) {
                        String standardized = matcher.group(1).toUpperCase() + matcher.group(2).toLowerCase();
                        return standardized + " S.A.S";
                }

                return raw + " Corp";
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
