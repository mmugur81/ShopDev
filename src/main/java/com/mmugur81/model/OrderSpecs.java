package com.mmugur81.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mugurel on 15.10.2016.
 * Order specifications for search query
 */

public class OrderSpecs {

    public static Specification<Order> bySearchCriteria(final OrderSearchCriteria osc) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (osc.user != null) {
                predicates.add(builder.equal(root.get(Order_.user), osc.user));
            }

            if (osc.status != null) {
                predicates.add(builder.equal(root.get(Order_.status), osc.status));
            }

            if (osc.createdBetween != null && osc.createdBetween.start != null && osc.createdBetween.end != null) {
                predicates.add(builder.between(
                        root.get(Order_.created), osc.createdBetween.start, osc.createdBetween.end
                ));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
