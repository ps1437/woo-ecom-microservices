package com.syscho.wocom.products.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${syscho.openapi.url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("ECommerce app");

        Contact contact = new Contact();
        contact.setEmail("praveen369soni@gmail.com");
        contact.setName("Praveen Kumar Soni");
        // contact.setUrl("https://github.com/ps1437/Grpc-graphql-fullstack");

        Info info = new Info()
                .title("Product Service API")
                .version("1.0")
                .contact(contact)
                .description("API documentation for the Product Service.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
