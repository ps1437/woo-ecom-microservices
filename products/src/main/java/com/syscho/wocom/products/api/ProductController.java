package com.syscho.wocom.products.api;

import com.syscho.wocom.products.dto.ProductDTO;
import com.syscho.wocom.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProducts(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> addAllProducts(@RequestBody List<ProductDTO> productDTOList) {
        productService.addAllProducts(productDTOList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/ids/{productIds}")
    public ResponseEntity<List<ProductDTO>> getProductByIds(@PathVariable List<Long> productIds) {
        List<ProductDTO> products = productService.getProductByIds(productIds);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
