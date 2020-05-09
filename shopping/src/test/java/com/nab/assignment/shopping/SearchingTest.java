package com.nab.assignment.shopping;

import com.nab.assignment.shopping.entity.Product;
import com.nab.assignment.shopping.exception.ShoppingException;
import com.nab.assignment.shopping.model.ProductSearchParam;
import com.nab.assignment.shopping.service.ProductSearchingSpecs;
import org.hibernate.query.criteria.internal.predicate.MemberOfPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.Map;

public class SearchingTest {

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
    public void searchProductByName() {
        Map<String, String> maps = new HashMap<>();
        maps.put("NAME", "ANKER");

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("name")).thenReturn(pathName);

        Expression nameExpression = Mockito.mock(Expression.class);
        Mockito.when(criteriaBuilderMock.lower(pathName)).thenReturn(nameExpression);

        Predicate namePredicate = Mockito.mock(Predicate.class);
        Mockito.when(criteriaBuilderMock.like(nameExpression, "%anker%")).thenReturn(namePredicate);

        Specification<Product> actual = productSearchingSpecs.searchProductByName(maps);
        Predicate actualPredicate = actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("name");
        Mockito.verifyNoMoreInteractions(rootMock);

        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).lower(pathName);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).like(nameExpression, "%anker%");
        Mockito.verifyNoMoreInteractions(criteriaBuilderMock);

        Mockito.verifyNoMoreInteractions(criteriaQueryMock, pathName, namePredicate);

        Assertions.assertEquals(namePredicate, actualPredicate);
    }

    @Test
    public void searchProductByBranchName() {
        Map<String, String> maps = new HashMap<>();
        maps.put("BRANCH_NAME", "ANKER");

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("branchName")).thenReturn(pathName);

        Expression nameExpression = Mockito.mock(Expression.class);
        Mockito.when(criteriaBuilderMock.lower(pathName)).thenReturn(nameExpression);

        Predicate namePredicate = Mockito.mock(Predicate.class);
        Mockito.when(criteriaBuilderMock.like(nameExpression, "%anker%")).thenReturn(namePredicate);

        Specification<Product> actual = productSearchingSpecs.searchProductByBranchName(maps);
        Predicate actualPredicate = actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("branchName");
        Mockito.verifyNoMoreInteractions(rootMock);

        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).lower(pathName);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).like(nameExpression, "%anker%");
        Mockito.verifyNoMoreInteractions(criteriaBuilderMock);

        Mockito.verifyNoMoreInteractions(criteriaQueryMock, pathName, namePredicate);

        Assertions.assertEquals(namePredicate, actualPredicate);
    }

    @Test
    public void searchProductByColor() {
        Map<String, String> maps = new HashMap<>();
        maps.put("COLOR", "RED");

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("color")).thenReturn(pathName);

        Expression nameExpression = Mockito.mock(Expression.class);
        Mockito.when(criteriaBuilderMock.lower(pathName)).thenReturn(nameExpression);

        Predicate namePredicate = Mockito.mock(Predicate.class);
        Mockito.when(criteriaBuilderMock.like(nameExpression, "%red%")).thenReturn(namePredicate);

        Specification<Product> actual = productSearchingSpecs.searchProductByColor(maps);
        Predicate actualPredicate = actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("color");
        Mockito.verifyNoMoreInteractions(rootMock);

        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).lower(pathName);
        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).like(nameExpression, "%red%");
        Mockito.verifyNoMoreInteractions(criteriaBuilderMock);

        Mockito.verifyNoMoreInteractions(criteriaQueryMock, pathName, namePredicate);

        Assertions.assertEquals(namePredicate, actualPredicate);
    }

    @Test
    public void searchProductByPrice() {
        Map<String, String> maps = new HashMap<>();
        maps.put("PRICE", "1-100");

        Path pathName = Mockito.mock(Path.class);
        Mockito.when(rootMock.get("price")).thenReturn(pathName);

        Predicate namePredicate = Mockito.mock(Predicate.class);
        Mockito.when(criteriaBuilderMock.between(pathName, 1d, 100d)).thenReturn(namePredicate);

        Specification<Product> actual = productSearchingSpecs.searchProductByPrice(maps);
        Predicate actualPredicate = actual.toPredicate(rootMock, criteriaQueryMock, criteriaBuilderMock);

        Mockito.verify(rootMock, Mockito.times(1)).get("price");
        Mockito.verifyNoMoreInteractions(rootMock);

        Mockito.verify(criteriaBuilderMock, Mockito.times(1)).between(pathName, 1d, 100d);
        Mockito.verifyNoMoreInteractions(criteriaBuilderMock);

        Mockito.verifyNoMoreInteractions(criteriaQueryMock, pathName, namePredicate);

        Assertions.assertEquals(namePredicate, actualPredicate);
    }

    @Test
    public void shouldThrowExceptionForInvalidParam() {
        ProductSearchParam productSearchParam = ProductSearchParam.withSearchKey("ANKER").searchBy("NAME,BRANCHNAME").build();
        Assertions.assertThrows(ShoppingException.class, () -> productSearchingSpecs.search(productSearchParam));
    }

}
