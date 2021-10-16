package dev.taljaard.training.trnmicroservbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dev.taljaard.training.trnmicroservbreweryclient.web.model.BeerDto;

@Component
@ConfigurationProperties(prefix = "service.provider", ignoreUnknownFields = false)
public class BreweryClient {

    public static final String BEER_PATH_V1 = "/api/v1/beer/";

    private String host;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(getBeerServicePath() + UUID.randomUUID(), BeerDto.class);
    }

    public URI createNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(getBeerServicePath(), beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(getBeerServicePath() + uuid, beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(getBeerServicePath() + uuid);
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String getBeerServicePath() {
        return String.format("%s%s", host, BEER_PATH_V1);
    }
}
