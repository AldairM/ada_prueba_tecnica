package com.ada.service.core;

import com.ada.model.core.TmpFillField;
import com.ada.repository.core.TmpFillFieldRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataInitializationTest {

    @Autowired
    private TmpFillFieldRepository tmpFillFieldRepository;

    @Test
    void testDataInitializedInTmpTable() {
        List<TmpFillField> data = tmpFillFieldRepository.findAll();

        assertThat(data).isNotEmpty();
        assertThat(data).hasSize(3); // 1 for company1 + 2 for company2 (v2 and v3)

        TmpFillField first = data.get(0);
        assertThat(first.getCompanyName()).isEqualTo("Tech Solutions Inc");
        assertThat(first.getApplicationName()).isEqualTo("Inventory Manager");
        assertThat(first.getVersion()).isEqualTo("1.0.0");
        assertThat(first.getCompanyId()).isNotNull();
        assertThat(first.getApplicationId()).isNotNull();
        assertThat(first.getVersionId()).isNotNull();
        assertThat(first.getVersionCompanyId()).isNotNull();
    }
}
