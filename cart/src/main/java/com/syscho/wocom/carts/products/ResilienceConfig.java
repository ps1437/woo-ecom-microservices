package com.syscho.wocom.carts.products;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorator;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ResilienceConfig {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    private final RetryRegistry retryRegistry;

    private final RateLimiterRegistry rateLimiterRegistry;

    private final ProductResilienceEventListener resilienceEventListener;

    @Bean
    public FeignDecorator feignDecorator() {

        Retry productServiceRetry = retryRegistry.retry("productService");
        productServiceRetry.getEventPublisher().onEvent(resilienceEventListener::onRetryEvent);

        CircuitBreaker productServiceCircuitBreaker = circuitBreakerRegistry.circuitBreaker("productService");
        productServiceCircuitBreaker.getEventPublisher().onEvent(resilienceEventListener::onCircuitBreakerEvent);

        return FeignDecorators.builder()
                .withCircuitBreaker(productServiceCircuitBreaker)
                .withRetry(productServiceRetry)
                .withRateLimiter(rateLimiterRegistry.rateLimiter("productServiceLimiter"))
                .build();
    }

}