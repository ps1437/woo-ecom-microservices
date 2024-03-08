//package com.syscho.woo.gateway.config;// GatewayConfig.java
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("product-service", r -> r.path("/api/products/**")
//                        .uri("lb://product-service"))
//                .route("order-service", r -> r.path("/api/orders/**")
//                        .uri("lb://order-service"))
//                .route("cart-service", r -> r.path("/api/carts/**")
//                        .uri("lb://CART-SERVICE"))
//                .route("user-service", r -> r.path("/api/users/**")
//                        .uri("http://localhost:8084"))
//                .build();
//    }
//}
//
///*
//Dynamically derived the service name and url -
//but the service name and url should match
//service name : product-service
//url  :/api/products/**
//expected UR: :api/product-service/**
//
//
//   .route("product-service", r -> r.path("/api/products/**")
//
//@Configuration
//public class GatewayConfig {
//
//    private final DiscoveryClient discoveryClient;
//
//    public GatewayConfig(DiscoveryClient discoveryClient) {
//        this.discoveryClient = discoveryClient;
//    }
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        RouteLocatorBuilder.Builder routes = builder.routes();
//
//        // Fetch service names dynamically
//        List<String> serviceNames = discoveryClient.getServices();
//
//        for (String serviceName : serviceNames) {
//            routes.route(serviceName, r -> r.path("/api/" + serviceName.toLowerCase() + "/**")
//                    .uri("lb://" + serviceName));
//        }
//
//        return routes.build();
//    }
//}
//
// */