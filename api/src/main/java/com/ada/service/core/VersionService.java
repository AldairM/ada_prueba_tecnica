package com.ada.service.core;

import com.ada.dto.CreateVersionRequest;
import com.ada.dto.VersionDTO;
import com.ada.model.core.Version;
import com.ada.repository.core.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository repository;
    private final com.ada.repository.core.ApplicationRepository applicationRepository;

    @Transactional
    public VersionDTO create(CreateVersionRequest request) {
        Version entity = new Version();
        entity.setVersion(request.getVersion());
        entity.setVersionDescription(request.getVersionDescription());

        com.ada.model.core.Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(
                        () -> new RuntimeException("Application not found with id: " + request.getApplicationId()));
        entity.setApplication(application);

        Version saved = repository.save(entity);
        return mapToDTO(saved);
    }

    public List<VersionDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public VersionDTO getById(Long id) {
        Version entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Version not found with id: " + id));
        return mapToDTO(entity);
    }

    @Transactional
    public VersionDTO update(Long id, CreateVersionRequest request) {
        Version entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Version not found with id: " + id));

        entity.setVersion(request.getVersion());
        entity.setVersionDescription(request.getVersionDescription());

        if (request.getApplicationId() != null) {
            com.ada.model.core.Application application = applicationRepository.findById(request.getApplicationId())
                    .orElseThrow(
                            () -> new RuntimeException("Application not found with id: " + request.getApplicationId()));
            entity.setApplication(application);
        }

        Version updated = repository.save(entity);
        return mapToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Version not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private VersionDTO mapToDTO(Version entity) {
        return VersionDTO.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .versionDescription(entity.getVersionDescription())
                .applicationId(entity.getApplication() != null ? entity.getApplication().getId() : null)
                .build();
    }
}
