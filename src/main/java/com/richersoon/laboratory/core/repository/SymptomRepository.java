package com.richersoon.laboratory.core.repository;

import com.richersoon.laboratory.core.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The symptom main repository
 */
public interface SymptomRepository extends JpaRepository<Symptom, String> {

    /**
     * Find by description
     * @param name the description
     * @return optional symptom
     */
    Optional<Symptom> findByDescription(String name);

    /**
     * Find by virus name
     * @param virusName the virus name
     * @return the symptoms
     */
    List<Symptom> findByVirus_Name(String virusName);
}
