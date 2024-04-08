package com.syscho.wocom.carts.products;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//TODO name not working so add URL
@FeignClient(name = "product-service", url = "${product-service.url}",
        configuration = ResilienceConfig.class)
public interface ProductFeignClient {

    @GetMapping("/api/products/{productId}")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackForGetProductById")
    @Retry(name = "productService", fallbackMethod = "fallbackForGetProductById")
    @RateLimiter(name = "productServiceLimiter")
    ProductDTO getProductById(@PathVariable Long productId);

    @GetMapping("/api/products/ids/{productIds}")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackForGetProductByIds")
    @Retry(name = "productService", fallbackMethod = "fallbackForGetProductByIds")
    List<ProductDTO> getProductByIds(@PathVariable List<Long> productId);

    default ProductDTO fallbackForGetProductById(Long productId, Throwable throwable) {
        return cachedProductData(productId);
    }

    default List<ProductDTO> fallbackForGetProductByIds(List<Long> productIds, Throwable throwable) {
        return cachedProductData(productIds);
    }

    private ProductDTO cachedProductData(Long productId) {
        return ProductDTO.builder().productId(productId).build();
    }

    private List<ProductDTO> cachedProductData(List<Long> productIds) {
        return List.of(ProductDTO.builder().productId(productIds.get(0)).build());
    }
}