package dev.taljaard.training.trnmicroservbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final RestCustomerizerProperties properties;

    public BlockingRestTemplateCustomizer(RestCustomerizerProperties properties) {
        this.properties = properties;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(properties.getConnectionMaxPoolTotal());
        connectionManager.setDefaultMaxPerRoute(properties.getConnectionMaxPoolConnectionsPerRoute());

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(properties.getRequestConfigConnectionTimeout())
                .setSocketTimeout(properties.getRequestConfigSocketTimeout()).build();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);

    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());

    }

}
