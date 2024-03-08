package com.syscho.wocom.products.mapper;

import com.syscho.wocom.products.dto.ProductDTO;
import com.syscho.wocom.products.repo.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ProductMapper {

    ProductDTO toProduct(ProductEntity productEntity);
    ProductEntity toProductEntity(ProductDTO productEntity);

    List<ProductEntity> toProductEntityList(List<ProductDTO> productDTO);

    List<ProductDTO> toProductList(List<ProductEntity> productEntitys);
}
