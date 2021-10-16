package dev.taljaard.training.trnmicroservbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.taljaard.training.trnmicroservbreweryclient.web.model.CustomerDto;

@SpringBootTest
public class CustomerClientTest {

    @Autowired
    private CustomerClient client;

    private CustomerDto getCustomer() {
        return CustomerDto.builder().name("Gilly Milly").build();
    }

    @Test
    public void getBeerById() {
        CustomerDto result = client.getCustomerById(UUID.randomUUID());
        assertNotNull(result);
    }

    @Test
    void createNewBeer() {
        URI uri = client.createCustomer(getCustomer());
        System.out.println(uri);
    }

    @Test
    void updateBeer() {
        client.updateCustomer(UUID.randomUUID(), getCustomer());
    }

    @Test
    void deleteBeer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}
