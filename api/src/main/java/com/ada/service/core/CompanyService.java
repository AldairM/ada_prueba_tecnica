package com.ada.service.core;

import com.ada.dto.CreateCompanyRequest;
import com.ada.dto.CompanyDTO;
import com.ada.model.core.Company;
import com.ada.repository.core.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    @Transactional
    public CompanyDTO create(CreateCompanyRequest request) {
        Company entity = new Company();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        Company saved = repository.save(entity);
        return mapToDTO(saved);
    }

    public List<CompanyDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO getById(Long id) {
        Company entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return mapToDTO(entity);
    }

    @Transactional
    public CompanyDTO update(Long id, CreateCompanyRequest request) {
        Company entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));

        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        Company updated = repository.save(entity);
        return mapToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private CompanyDTO mapToDTO(Company entity) {
        return CompanyDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
