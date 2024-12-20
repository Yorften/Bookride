package com.bookride.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class DriverDao {
    @PersistenceContext
    private EntityManager entityManager;

    public double calculateOccupationRate() {
        String jpql = "SELECT (SUM(CASE WHEN d.status = 'IN_COURSE' THEN 1 ELSE 0 END) * 1.0 / COUNT(d)) * 100 " +
                "FROM Driver d";
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        return query.getSingleResult();
    }

    public List<Object[]> analyzeAvailabilityPeriods() {
        String jpql = "SELECT d.availabilityStart, d.availabilityEnd, COUNT(d) " +
                "FROM Driver d " +
                "GROUP BY d.availabilityStart, d.availabilityEnd";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

    public List<Object[]> getStatusDistribution() {
        String jpql = "SELECT d.status, COUNT(d) " +
                "FROM Driver d " +
                "GROUP BY d.status";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        return query.getResultList();
    }
}
