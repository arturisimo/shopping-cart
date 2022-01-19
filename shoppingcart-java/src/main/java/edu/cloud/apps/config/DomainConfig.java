package edu.cloud.apps.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("edu.cloud.apps.domain")
@EnableJpaRepositories("edu.cloud.apps.repos")
@EnableTransactionManagement
public class DomainConfig {
}
