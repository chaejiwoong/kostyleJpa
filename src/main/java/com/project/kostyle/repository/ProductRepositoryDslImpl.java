package com.project.kostyle.repository;

import com.project.kostyle.dto.product.SearchDto;
import com.project.kostyle.entity.Product;
import com.project.kostyle.entity.QProduct;
import com.project.kostyle.entity.QProductImg;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.project.kostyle.entity.QProduct.*;
import static com.project.kostyle.entity.QProductImg.*;

public class ProductRepositoryDslImpl implements ProductRepositoryDsl {
    private final JPAQueryFactory queryFactory;

    public ProductRepositoryDslImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Product> findAllByDsl(Pageable pageable, SearchDto search) {

        List<Product> result = queryFactory.select(product)
                .from(product)
                .where(pcnoEq(search.getPcno()), scnoEq(search.getScno()), nameEq(search.getName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(product)
                .from(product)
                .where(pcnoEq(search.getPcno()), scnoEq(search.getScno()), nameEq(search.getName()))
                .fetch().size();

        return new PageImpl<>(result, pageable, total);
    }

    private BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? product.name.contains(name) : null;
    }

    private BooleanExpression pcnoEq(Long pcno) {
        return pcno != null ? product.subCategory.parentCategory.pcno.eq(pcno) : null;
    }

    private BooleanExpression scnoEq(Long scno) {
        return scno != null ? product.subCategory.scno.eq(scno) : null;
    }
}

