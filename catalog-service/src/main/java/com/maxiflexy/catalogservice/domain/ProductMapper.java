package com.maxiflexy.catalogservice.domain;

public class ProductMapper {

    public static ProductDTO toProductDTO(ProductEntity productEntity){
        return new ProductDTO(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }
}
