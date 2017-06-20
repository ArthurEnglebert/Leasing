package com.citobi.leasing.domain;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CarSpecifications {
    /**
     * Specification to get all cars that are available (LockStatus.NONE)
     * @return the specification to use in JPA
     */
    public static Specification<Car> carIsAvailable() {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //todo change string to something more robust that will not compile if we change the name of the attribute Car.lockStatus
                return criteriaBuilder.equal(root.get("lockStatus"), LockStatus.NONE);
            }
        };
    }

    /**
     * Specification to get all cars with a specific Model
     * @param model the model that the cars must match
     * @return the specification to use in JPA
     */
    public static Specification<Car> carsWithModel(final Model model) {
        return new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Model>get("model"), model);
            }
        };
    }
}
