package com.nab.assignment.shopping.service;

import com.nab.assignment.shopping.entity.Product;
import com.nab.assignment.shopping.exception.ShoppingException;
import com.nab.assignment.shopping.model.ProductSearchParam;
import com.nab.assignment.shopping.model.SearchBy;
import com.nab.assignment.shopping.model.SortBy;
import com.nab.assignment.shopping.model.SortByType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductSearchingSpecs implements Searchable<Product, ProductSearchParam>, Sortable<Product, ProductSearchParam> {

    @Autowired
    public Specification<Product> search(ProductSearchParam productSearchParam) {z

        String[] searchByArray = productSearchParam.getSearchBy().split(",");
        String[] searchKeyArray = productSearchParam.getSearchKey().split(",");


        if (searchByArray.length != searchKeyArray.length) {
            throw new ShoppingException("Invalid Search By and Search Key Parameters");
        }

        Map<String, String> maps = IntStream.range(0, searchByArray.length).boxed().collect(Collectors.toMap(i -> searchByArray[i], i -> searchKeyArray[i]));

        return Specification.where(sort(productSearchParam)).
                and(searchProductByName(maps)).
                and(searchProductByBranchName(maps).
                and(searchProductByColor(maps)).
                and(searchProductByPrice(maps)));
    }

    @Autowired
    public Specification<Product> sort(ProductSearchParam productSearchParam) {

        String[] sortByArr = productSearchParam.getSortBy().split(" ");

        if (sortByArr != null && sortByArr.length < 2) {
            throw new ShoppingException("Invalid Sort By Parameter");
        }

        return (root, query, criteriaBuilder) -> {
            String sortBy = sortByArr[0];
            String sortType = sortByArr[1];

            if (sortType.equalsIgnoreCase(SortByType.ASC.toString())) {
                if (sortBy.equalsIgnoreCase(SortBy.NAME.toString())) {
                    query.orderBy(criteriaBuilder.asc(root.get("name")));
                } else if (sortBy.equalsIgnoreCase(SortBy.BRANCH_NAME.toString())) {
                    query.orderBy(criteriaBuilder.asc(root.get("branchName")));
                } else if (sortBy.equalsIgnoreCase(SortBy.PRICE.toString())) {
                    query.orderBy(criteriaBuilder.asc(root.get("price")));
                }
            } else {
                if (sortBy.equalsIgnoreCase(SortBy.NAME.toString())) {
                    query.orderBy(criteriaBuilder.desc(root.get("name")));
                } else if (sortBy.equalsIgnoreCase(SortBy.BRANCH_NAME.toString())) {
                    query.orderBy(criteriaBuilder.desc(root.get("branchName")));
                } else if (sortBy.equalsIgnoreCase(SortBy.PRICE.toString())) {
                    query.orderBy(criteriaBuilder.desc(root.get("price")));
                }
            }

            return null;
        };
    }

    public Specification<Product> searchProductByName(Map<String, String> maps) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + maps.getOrDefault(SearchBy.NAME.toString(), "").toLowerCase() + "%");
    }

    public Specification<Product> searchProductByBranchName(Map<String, String> maps) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("branchName")), "%" + maps.getOrDefault(SearchBy.BRANCH_NAME.toString(), "").toLowerCase() + "%");
    }

    public Specification<Product> searchProductByColor(Map<String, String> maps) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("color")), "%" + maps.getOrDefault(SearchBy.COLOR.toString(), "").toLowerCase() + "%");
    }

    public Specification<Product> searchProductByPrice(Map<String, String> maps) {
        return (root, query, criteriaBuilder) -> {
            String[] priceArray = maps.getOrDefault(SearchBy.PRICE.toString(), "").split("-");
            if (priceArray != null && priceArray.length >= 2) {
                return criteriaBuilder.between(root.get("price"), Double.valueOf(priceArray[0]), Double.valueOf(priceArray[1]));
            } else {
                return null;
            }
        };
    }

}
