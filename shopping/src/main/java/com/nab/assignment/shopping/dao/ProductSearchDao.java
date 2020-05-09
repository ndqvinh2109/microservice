package com.nab.assignment.shopping.dao;

import com.nab.assignment.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSearchDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
