package com.syscho.wocom.products;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    public void addProducts(ProductDTO productDTO) {
        ProductEntity newProducts = productMapper.toProduct(productDTO);

        productRepository.save(newProducts);
    }


    public void addAllProducts(List<ProductDTO> productDTO) {
        List<ProductEntity> newProducts = productMapper.toProducts(productDTO);

        productRepository.saveAll(newProducts);
    }

    public ProductDTO getProductById(Long productId) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return productMapper.toProduct(product.get());
        } else {
            throw new RuntimeException("No Product found with id " + productId);
        }
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
