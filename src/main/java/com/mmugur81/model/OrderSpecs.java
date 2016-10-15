package com.mmugur81.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mugurel on 15.10.2016.
 * Order specifications for search query
 */

public class OrderSpecs {

    public static Specification<Order> byUserStatusDateInterval(
        final User user,
        final Order.Status status,
        final Date d1,
        final Date d2
    ) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user != null) {
                predicates.add(builder.equal(root.get(Order_.user), user));
            }

            if (status != null) {
                predicates.add(builder.equal(root.get(Order_.status), status));
            }

            if (d1 != null && d2 != null) {
                predicates.add(builder.between(root.get(Order_.created), d1, d2));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
