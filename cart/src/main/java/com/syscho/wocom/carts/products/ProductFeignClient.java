package com.syscho.wocom.carts.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-api", url = "http://localhost:8080/api") // Update the URL accordingly
public interface ProductFeignClient {

    @GetMapping("/products/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);

    @GetMapping("/products/ids/{productIds}")
    List<ProductDTO> getProductByIds(@PathVariable List<Long> productId);
}
