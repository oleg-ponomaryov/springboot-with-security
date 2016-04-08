package com.quantlance.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.quantlance.domain"})
@EnableJpaRepositories(basePackages = {"com.quantlance.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
