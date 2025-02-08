package com.maxiflexy.catalogservice.web.controller;

import com.maxiflexy.catalogservice.domain.PagedResult;
import com.maxiflexy.catalogservice.domain.ProductDTO;
import com.maxiflexy.catalogservice.domain.ProductEntity;
import com.maxiflexy.catalogservice.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductDTO> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }
}
