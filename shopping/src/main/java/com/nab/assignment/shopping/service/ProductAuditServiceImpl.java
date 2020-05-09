package com.nab.assignment.shopping.service;

import com.nab.assignment.shopping.dao.ProductAuditDao;
import com.nab.assignment.shopping.entity.ProductAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAuditServiceImpl implements ProductAuditService {

    @Autowired
    private ProductAuditDao productAuditDao;

    @Override
    public void saveProductAudit(ProductAudit productAudit) {
        productAuditDao.save(productAudit);
    }

}
