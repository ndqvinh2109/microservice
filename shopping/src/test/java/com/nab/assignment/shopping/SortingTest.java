package com.nab.assignment.shopping;

import com.nab.assignment.shopping.entity.Product;
import com.nab.assignment.shopping.exception.ShoppingException;
import com.nab.assignment.shopping.model.ProductSearchParam;
import com.nab.assignment.shopping.service.ProductSearchingSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SortingTest {

    private CriteriaBuilder criteriaBuilderMock;
    private CriteriaQuery criteriaQueryMock;
    private Root<Product> rootMock;
    private ProductSearchingSpecs productSearchingSpecs;

    @BeforeEach
    public void init() {
        criteriaBuilderMock = Mockito.mock(CriteriaBuilder.class);
        criteriaQueryMock = Mockito.mock(CriteriaQuery.class);
        rootMock = Mockito.mock(Root.class);
        productSearchingSpecs = new ProductSearchingSpecs();
    }

    @Test
    public void shouldThrowExceptionForInvalidParam() {
        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("NAME").build();
        Assertions.assertThrows(ShoppingException.class, () -> productSearchingSpecs.sort(productSearchParam));
    }

    @Test
    public void sortingByName_ASC() {

        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("NAME ASC").build();

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("name")).thenReturn(pathName);

        Order orderMock = Mockito.mock(Order.class);
        Mockito.when(criteriaBuilderMock.asc(pathName)).thenReturn(orderMock);

        Specification<Product> actual = productSearchingSpecs.sort(productSearchParam);
        actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("name");
        Mockito.verify(criteriaQueryMock, Mockito.times(1)).orderBy(orderMock);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).asc(pathName);

    }

    @Test
    public void sortingByName_DESC() {

        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("NAME DESC").build();

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("name")).thenReturn(pathName);

        Order orderMock = Mockito.mock(Order.class);
        Mockito.when(criteriaBuilderMock.desc(pathName)).thenReturn(orderMock);

        Specification<Product> actual = productSearchingSpecs.sort(productSearchParam);
        actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("name");
        Mockito.verify(criteriaQueryMock, Mockito.times(1)).orderBy(orderMock);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).desc(pathName);

    }

    @Test
    public void sortingByBranchName_ASC() {

        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("BRANCH_NAME ASC").build();

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("branchName")).thenReturn(pathName);

        Order orderMock = Mockito.mock(Order.class);
        Mockito.when(criteriaBuilderMock.asc(pathName)).thenReturn(orderMock);

        Specification<Product> actual = productSearchingSpecs.sort(productSearchParam);
        actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("branchName");
        Mockito.verify(criteriaQueryMock, Mockito.times(1)).orderBy(orderMock);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).asc(pathName);
    }

    @Test
    public void sortingByBranchName_DESC() {

        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("BRANCH_NAME DESC").build();

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("branchName")).thenReturn(pathName);

        Order orderMock = Mockito.mock(Order.class);
        Mockito.when(criteriaBuilderMock.desc(pathName)).thenReturn(orderMock);

        Specification<Product> actual = productSearchingSpecs.sort(productSearchParam);
        actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("branchName");
        Mockito.verify(criteriaQueryMock, Mockito.times(1)).orderBy(orderMock);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).desc(pathName);
    }

    @Test
    public void sortingByPrice_ASC() {

        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("PRICE ASC").build();

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("price")).thenReturn(pathName);

        Order orderMock = Mockito.mock(Order.class);
        Mockito.when(criteriaBuilderMock.asc(pathName)).thenReturn(orderMock);

        Specification<Product> actual = productSearchingSpecs.sort(productSearchParam);
        actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("price");
        Mockito.verify(criteriaQueryMock, Mockito.times(1)).orderBy(orderMock);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).asc(pathName);
    }

    @Test
    public void sortingByPrice_DESC() {

        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME").sortBy("PRICE DESC").build();

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("price")).thenReturn(pathName);

        Order orderMock = Mockito.mock(Order.class);
        Mockito.when(criteriaBuilderMock.desc(pathName)).thenReturn(orderMock);

        Specification<Product> actual = productSearchingSpecs.sort(productSearchParam);
        actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("price");
        Mockito.verify(criteriaQueryMock, Mockito.times(1)).orderBy(orderMock);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).desc(pathName);
    }

}
