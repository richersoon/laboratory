package com.richersoon.laboratory.core.repository;

import com.richersoon.laboratory.core.model.Virus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The virus main repository
 */
public interface VirusRepository extends JpaRepository<Virus, String> {

    /**
     * Find by name
     * @param name the name
     * @return optional virus
     */
    Optional<Virus> findByName(String name);
}
