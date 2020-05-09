package com.nab.assignment.shopping.dao;

import com.nab.assignment.shopping.entity.ProductAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAuditDao extends JpaRepository<ProductAudit, Long> {

}
