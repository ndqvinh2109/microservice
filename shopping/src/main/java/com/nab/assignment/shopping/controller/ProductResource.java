package com.nab.assignment.shopping.controller;

import com.nab.assignment.shopping.dto.ProductResponse;
import com.nab.assignment.shopping.model.ProductSearchParam;
import com.nab.assignment.shopping.service.ProductSearchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductResource {

    @Autowired
    private ProductSearchingService productSearchingService;

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam(name = "sortBy") String sortBy,
                                                               @RequestParam(name = "searchBy") String searchBy,
                                                               @RequestParam(name = "productSearchKey") String productSearchKey) {

        return ResponseEntity.ok().body(productSearchingService.searchProduct(ProductSearchParam.withSearchKey(productSearchKey).
                searchBy(searchBy).
                sortBy(sortBy).
                build()));
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productSearchingService.findProductById(id));
    }

}
