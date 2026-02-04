package com.ada.controller.core;

import com.ada.dto.CreateVersionCompanyRequest;
import com.ada.dto.VersionCompanyDTO;
import com.ada.service.core.VersionCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/versioncompanies")
@RequiredArgsConstructor
public class VersionCompanyController {

    private final VersionCompanyService service;

    @GetMapping
    public ResponseEntity<List<VersionCompanyDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VersionCompanyDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<VersionCompanyDTO> create(@RequestBody CreateVersionCompanyRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VersionCompanyDTO> update(@PathVariable Long id, @RequestBody CreateVersionCompanyRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
