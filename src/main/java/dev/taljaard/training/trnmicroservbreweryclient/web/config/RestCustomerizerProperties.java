package dev.taljaard.training.trnmicroservbreweryclient.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "rest.customizer")
public class RestCustomerizerProperties {

    private int connectionMaxPoolTotal;
    private int connectionMaxPoolConnectionsPerRoute;
    private int requestConfigConnectionTimeout;
    private int requestConfigSocketTimeout;

}
