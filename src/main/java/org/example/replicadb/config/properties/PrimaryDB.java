package org.example.replicadb.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.primary")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class PrimaryDB {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Long idleTimeout;
    private Integer minimumIdle;
}
