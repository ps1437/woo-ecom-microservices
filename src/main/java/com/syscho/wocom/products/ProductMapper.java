package com.syscho.wocom.products;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ProductMapper {

    ProductDTO toProduct(ProductEntity productEntity);
    ProductEntity toProduct(ProductDTO productEntity);

    List<ProductEntity> toProducts(List<ProductDTO> productDTO);
}
