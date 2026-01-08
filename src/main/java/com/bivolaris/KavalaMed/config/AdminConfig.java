package com.bivolaris.KavalaMed.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.admin")
@Data
public class AdminConfig {

    private String key;
}
