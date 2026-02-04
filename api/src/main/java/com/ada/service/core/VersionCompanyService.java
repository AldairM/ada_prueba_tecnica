package com.ada.service.core;

import com.ada.dto.CreateVersionCompanyRequest;
import com.ada.dto.VersionCompanyDTO;
import com.ada.model.core.VersionCompany;
import com.ada.repository.core.VersionCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VersionCompanyService {

    private final VersionCompanyRepository repository;
    private final com.ada.repository.core.CompanyRepository companyRepository;
    private final com.ada.repository.core.VersionRepository versionRepository;

    @Transactional
    public VersionCompanyDTO create(CreateVersionCompanyRequest request) {
        VersionCompany entity = new VersionCompany();
        entity.setVersionCompanyDescription(request.getVersionCompanyDescription());

        com.ada.model.core.Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));
        entity.setCompany(company);

        com.ada.model.core.Version version = versionRepository.findById(request.getVersionId())
                .orElseThrow(() -> new RuntimeException("Version not found with id: " + request.getVersionId()));
        entity.setVersion(version);

        VersionCompany saved = repository.save(entity);
        return mapToDTO(saved);
    }

    public List<VersionCompanyDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public VersionCompanyDTO getById(Long id) {
        VersionCompany entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VersionCompany not found with id: " + id));
        return mapToDTO(entity);
    }

    @Transactional
    public VersionCompanyDTO update(Long id, CreateVersionCompanyRequest request) {
        VersionCompany entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VersionCompany not found with id: " + id));

        entity.setVersionCompanyDescription(request.getVersionCompanyDescription());

        if (request.getCompanyId() != null) {
            com.ada.model.core.Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));
            entity.setCompany(company);
        }

        if (request.getVersionId() != null) {
            com.ada.model.core.Version version = versionRepository.findById(request.getVersionId())
                    .orElseThrow(() -> new RuntimeException("Version not found with id: " + request.getVersionId()));
            entity.setVersion(version);
        }

        VersionCompany updated = repository.save(entity);
        return mapToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("VersionCompany not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private VersionCompanyDTO mapToDTO(VersionCompany entity) {
        return VersionCompanyDTO.builder()
                .id(entity.getId())
                .versionCompanyDescription(entity.getVersionCompanyDescription())
                .companyId(entity.getCompany() != null ? entity.getCompany().getId() : null)
                .versionId(entity.getVersion() != null ? entity.getVersion().getId() : null)
                .build();
    }
}
