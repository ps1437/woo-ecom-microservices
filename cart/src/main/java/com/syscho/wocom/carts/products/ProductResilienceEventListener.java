package com.syscho.wocom.carts.products;

import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnErrorEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnSuccessEvent;
import io.github.resilience4j.retry.event.RetryEvent;
import io.github.resilience4j.retry.event.RetryOnRetryEvent;
import io.github.resilience4j.retry.event.RetryOnErrorEvent;
import io.github.resilience4j.retry.event.RetryOnSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductResilienceEventListener {

    public void onCircuitBreakerEvent(CircuitBreakerEvent event) {
        if (event instanceof CircuitBreakerOnStateTransitionEvent stateTransitionEvent) {
            log.info("CircuitBreaker state transition: {} -> {}", stateTransitionEvent.getStateTransition().getFromState(), stateTransitionEvent.getStateTransition().getToState());
        } else if (event instanceof CircuitBreakerOnErrorEvent errorEvent) {
            log.error("CircuitBreaker opened due to error: {}", errorEvent.getThrowable().getMessage());
        } else if (event instanceof CircuitBreakerOnSuccessEvent) {
            log.info("CircuitBreaker closed after being half-open");
        }
    }

    public void onRetryEvent(RetryEvent event) {
        if (event instanceof RetryOnRetryEvent retryEvent) {
            log.info("Retry attempt: name : {}  {}", retryEvent.getName(), retryEvent.getNumberOfRetryAttempts());
        } else if (event instanceof RetryOnErrorEvent errorEvent) {
            // Log error event
            log.error("Retry failed with error: {}", errorEvent.getLastThrowable().getMessage());
        } else if (event instanceof RetryOnSuccessEvent) {
            // Log success event
            log.info("Retry succeeded after {} attempts", event.getNumberOfRetryAttempts());
        }
    }
}
