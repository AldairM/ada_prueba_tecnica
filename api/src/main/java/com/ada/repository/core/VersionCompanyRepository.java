package com.ada.repository.core;

import com.ada.model.core.VersionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionCompanyRepository extends JpaRepository<VersionCompany, Long> {
}
