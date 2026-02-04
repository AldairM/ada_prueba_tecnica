package com.ada.repository.core;

import com.ada.model.core.TmpFillField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpFillFieldRepository extends JpaRepository<TmpFillField, Long> {
}
