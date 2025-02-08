package com.maxiflexy.catalogservice.web.controller;

import com.maxiflexy.catalogservice.domain.PagedResult;
import com.maxiflexy.catalogservice.domain.ProductDTO;
import com.maxiflexy.catalogservice.domain.ProductEntity;
import com.maxiflexy.catalogservice.domain.ProductService;
import com.maxiflexy.catalogservice.web.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductDTO> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductDTO> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
