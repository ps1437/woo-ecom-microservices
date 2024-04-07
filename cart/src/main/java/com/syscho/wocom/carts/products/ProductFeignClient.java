package com.syscho.wocom.carts.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//TODO name not working so add URL
@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductFeignClient {

    @GetMapping("/api/products/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);

    @GetMapping("/api/products/ids/{productIds}")
    List<ProductDTO> getProductByIds(@PathVariable List<Long> productId);
}
