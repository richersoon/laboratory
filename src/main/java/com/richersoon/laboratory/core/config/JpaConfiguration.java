package com.richersoon.laboratory.core.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA related configuration
 */
@EnableJpaRepositories("com.richersoon.laboratory.core.repository")
public class JpaConfiguration {
}
