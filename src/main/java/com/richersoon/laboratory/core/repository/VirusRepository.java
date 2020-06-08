package com.richersoon.laboratory.core.repository;

import com.richersoon.laboratory.core.model.Virus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirusRepository extends JpaRepository<Virus, String> {
}
