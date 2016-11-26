package com.mmugur81.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mugurel on 15.10.2016.
 * Product specifications for search query
 */

public class ProductSpecs {

    public static Specification<Product> bySearchCriteria(final ProductSearchCriteria psc) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (psc.category != null) {
                predicates.add(builder.equal(root.get(Product_.category), psc.category));
            }

            if (psc.nameLike != null) {
                predicates.add(builder.like(root.get(Product_.name), "%" + psc.nameLike + "%"));
            }

            if (psc.currency != null) {
                predicates.add(builder.equal(root.get(Product_.price).get(Price_.currency), psc.currency));
            }

            if (psc.priceBetween != null && psc.priceBetween.start != null && psc.priceBetween.end != null) {
                predicates.add(builder.between(
                        root.get(Product_.price).get(Price_.price), psc.priceBetween.start, psc.priceBetween.end
                ));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
