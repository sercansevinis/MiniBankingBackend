package com.mini.banking.demo.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.RequiredArgsConstructor;

@Configuration
@EntityScan(basePackages = { "com.mini.banking.demo.core"
})
@ComponentScan(basePackages = { "com.mini.banking.demo.core", "com.mini.banking.demo.service" })
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.mini.banking.demo.core", "com.mini.banking.demo.service" })
@EnableJpaAuditing
@RequiredArgsConstructor
public class CoreConfig {

}
