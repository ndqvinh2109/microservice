package com.nab.assignment.shopping.service;

import com.nab.assignment.shopping.dto.ProductResponse;
import com.nab.assignment.shopping.model.ProductSearchParam;

import java.util.List;

public interface ProductSearchingService {
    List<ProductResponse> searchProduct(ProductSearchParam searchProducParam);
    ProductResponse findProductById(Long id);
}
