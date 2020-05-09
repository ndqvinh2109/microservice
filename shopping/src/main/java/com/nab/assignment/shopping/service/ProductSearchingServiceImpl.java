package com.nab.assignment.shopping.service;

import com.nab.assignment.shopping.dao.ProductAuditDao;
import com.nab.assignment.shopping.dao.ProductSearchDao;
import com.nab.assignment.shopping.dto.ProductResponse;
import com.nab.assignment.shopping.entity.Product;
import com.nab.assignment.shopping.entity.ProductAudit;
import com.nab.assignment.shopping.exception.ShoppingException;
import com.nab.assignment.shopping.model.ProductSearchParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSearchingServiceImpl implements ProductSearchingService {

    @Autowired
    private ProductSearchDao productSearchDao;

    @Autowired
    private ProductAuditDao productAuditDao;

    @Override
    public List<ProductResponse> searchProduct(ProductSearchParam searchProducParam) {
        ProductAudit productAudit = new ProductAudit();
        productAudit.setAuditAction("SEARCHING PRODUCT");
        productAudit.setAuditActionTime(new Date());

        productAuditDao.save(productAudit);

        Searchable searchable = new ProductSearchingSpecs();
        List<Product> products = productSearchDao.findAll(searchable.search(searchProducParam));
        productSearchDao.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return products.stream().map(p -> modelMapper.map(p, ProductResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(Long id) {
        ProductAudit productAudit = new ProductAudit();
        productAudit.setAuditAction("FIND PRODUCT BY ID");
        productAudit.setAuditActionTime(new Date());
        productAudit.setAuditReference(id.toString());

        productAuditDao.save(productAudit);

        Optional<Product> optionalProduct = productSearchDao.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new ShoppingException("Unable to get product detail due to invalid id");
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(optionalProduct.get(), ProductResponse.class);
    }


}
