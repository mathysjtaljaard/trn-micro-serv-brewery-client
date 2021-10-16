package dev.taljaard.training.trnmicroservbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dev.taljaard.training.trnmicroservbreweryclient.web.model.CustomerDto;

@Component
@ConfigurationProperties(prefix = "service.provider", ignoreUnknownFields = false)
public class CustomerClient {

    public static final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    private String host;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID id) {
        return restTemplate.getForObject(getCustomerServicePath() + UUID.randomUUID(), CustomerDto.class);
    }

    public URI createCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(getCustomerServicePath(), customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(getCustomerServicePath() + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(getCustomerServicePath() + uuid);
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String getCustomerServicePath() {
        return String.format("%s%s", host, CUSTOMER_PATH_V1);
    }
}
