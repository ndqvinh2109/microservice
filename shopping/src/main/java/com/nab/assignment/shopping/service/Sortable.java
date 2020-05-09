package com.nab.assignment.shopping.service;


import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface Sortable<T, P> {
    Specification<T> sort(P param);
}
