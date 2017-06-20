package com.citobi.leasing.domain;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReservationSpecifications {
    /**
     * Specification to get all reservation that are inside the specified period
     * @param start the start of the period
     * @param end the end of the period
     * @return the specification to use in JPA
     */
    public static Specification<Reservation> inPeriod(final Date start, final Date end) {
        return new Specification<Reservation>() {
            @Override
            public Predicate toPredicate(Root<Reservation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(builder.lessThanOrEqualTo(root.<Date>get("start"), end));
                predicates.add(builder.greaterThanOrEqualTo(root.<Date>get("end"), start));
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
