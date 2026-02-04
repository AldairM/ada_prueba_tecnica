package com.ada.service.core;

import com.ada.dto.CreateApplicationRequest;
import com.ada.dto.ApplicationDTO;
import com.ada.model.core.Application;
import com.ada.repository.core.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final com.ada.repository.core.CompanyRepository companyRepository;

    @Transactional
    public ApplicationDTO create(CreateApplicationRequest request) {
        Application entity = new Application();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        com.ada.model.core.Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));
        entity.setCompany(company);

        Application saved = repository.save(entity);
        return mapToDTO(saved);
    }

    public List<ApplicationDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ApplicationDTO getById(Long id) {
        Application entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
        return mapToDTO(entity);
    }

    @Transactional
    public ApplicationDTO update(Long id, CreateApplicationRequest request) {
        Application entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        if (request.getCompanyId() != null) {
            com.ada.model.core.Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));
            entity.setCompany(company);
        }

        Application updated = repository.save(entity);
        return mapToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Application not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private ApplicationDTO mapToDTO(Application entity) {
        return ApplicationDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .companyId(entity.getCompany() != null ? entity.getCompany().getId() : null)
                .build();
    }
}
