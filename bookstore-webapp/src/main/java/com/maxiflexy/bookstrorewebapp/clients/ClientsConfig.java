package com.maxiflexy.bookstrorewebapp.clients;

import com.maxiflexy.bookstrorewebapp.clients.catalog.CatalogServiceClient;
import com.maxiflexy.bookstrorewebapp.clients.orders.OrderServiceClient;
import com.maxiflexy.bookstrorewebapp.config.ApplicationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
class ClientsConfig {
//    private final ApplicationProperties properties;
//
//    ClientsConfig(ApplicationProperties properties) {
//        this.properties = properties;
//    }
//
//    @Bean
//    RestClientCustomizer restClientCustomizer() {
//        return restClientBuilder -> restClientBuilder
//                .baseUrl(properties.apiGatewayUrl())
//                .requestFactory(() -> (org.springframework.http.client.ClientHttpRequest) createClientHttpRequestFactory());  // FIXED METHOD REFERENCE
//    }
//
//    private static ClientHttpRequestFactory createClientHttpRequestFactory() { // MAKE IT STATIC
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout((int) Duration.ofSeconds(5).toMillis());
//        factory.setReadTimeout((int) Duration.ofSeconds(5).toMillis());
//        return factory;
//    }

    @Bean
    CatalogServiceClient catalogServiceClient(RestClient.Builder builder) {
        RestClient restClient = builder.build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(CatalogServiceClient.class);
    }

    @Bean
    OrderServiceClient orderServiceClient(RestClient.Builder builder) {
        RestClient restClient = builder.build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(OrderServiceClient.class);
    }
}
