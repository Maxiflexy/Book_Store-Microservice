package com.maxiflexy.catalogservice.domain;

import com.maxiflexy.catalogservice.ApplicationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    public ProductService(ProductRepository productRepository, ApplicationProperties properties) {
        this.productRepository = productRepository;
        this.properties = properties;
    }

    public PagedResult<ProductDTO> getProducts(int pageNo){
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;

        Sort sort = Sort.by("name").ascending();

        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<ProductDTO> productsPage = productRepository.findAll(pageable)
                .map(ProductMapper::toProductDTO);

        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious()
        );
    }
}
