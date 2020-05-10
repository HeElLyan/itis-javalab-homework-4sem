package ru.he.jlmq.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.he.jlmq.server.repositories")
public class JpaConfig {
}
