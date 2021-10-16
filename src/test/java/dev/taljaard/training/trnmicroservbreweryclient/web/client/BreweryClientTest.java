package dev.taljaard.training.trnmicroservbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.taljaard.training.trnmicroservbreweryclient.web.model.BeerDto;

@SpringBootTest
public class BreweryClientTest {

    @Autowired
    private BreweryClient client;

    private BeerDto getTestBeer() {
        return BeerDto.builder().beerName("happy").beerStyle("gnome").upc(1L).build();
    }

    @Test
    public void getBeerById() {
        BeerDto result = client.getBeerById(UUID.randomUUID());
        assertNotNull(result);
    }

    @Test
    void createNewBeer() {
        URI uri = client.createNewBeer(getTestBeer());
        System.out.println(uri);
    }

    @Test
    void updateBeer() {
        client.updateBeer(UUID.randomUUID(), getTestBeer());
    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }
}
