package com.syscho.wocom.products;


import com.syscho.wocom.products.dto.ProductDTO;
import com.syscho.wocom.products.mapper.ProductMapper;
import com.syscho.wocom.products.repo.ProductEntity;
import com.syscho.wocom.products.repo.ProductRepository;
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
        ProductEntity newProducts = productMapper.toProductEntity(productDTO);

        productRepository.save(newProducts);
    }


    public void addAllProducts(List<ProductDTO> productDTO) {
        List<ProductEntity> newProducts = productMapper.toProductEntityList(productDTO);

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

    public List<ProductDTO> getProductByIds(List<Long> productIds) {
        List<ProductEntity> products = productRepository.findAllById(productIds);

        if (!products.isEmpty()) {
            return productMapper.toProductList(products);
        } else {
            throw new RuntimeException("No Products found with ids " + productIds);
        }
    }

}
