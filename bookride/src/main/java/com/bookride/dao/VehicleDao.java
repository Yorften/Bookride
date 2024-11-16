package com.bookride.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Analytics of average mileage per vehicle type.
     * 
     * @return a list of average mileage per vehicle type
     */
    @Transactional
    public List<Object[]> getAverageMileageByVehicleType() {

        String jpql = "SELECT v.vehicleType, AVG(v.mileage) FROM Vehicle v GROUP BY v.vehicleType ORDER BY CASE v.vehicleType WHEN 'SEDAN' THEN 1 WHEN 'MINIBUS' THEN 2 WHEN 'VAN' THEN 3 ELSE 4 END";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        return query.getResultList();
    }


    /**
     * Analytics of usage rate per vehicle type.
     * 
     * @return a list of usage rate per vehicle type
     */
    @Transactional
    public List<Object[]> getAverageUsageRateByVehicleType() {

        String jpql = "SELECT v.vehicleType, (COUNT(v) * 1.0 / (SELECT COUNT(v2) FROM Vehicle v2)) FROM Vehicle v WHERE v.status = 'IN_COURSE' GROUP BY v.vehicleType ORDER BY CASE v.vehicleType WHEN 'SEDAN' THEN 1 WHEN 'MINIBUS' THEN 2 WHEN 'VAN' THEN 3 ELSE 4 END";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        return query.getResultList();
    }


    /**
     * Analytics of total number of vehicles per type.
     * 
     * @return a list of number of vehicles per type
     */
    @Transactional
    public List<Object[]> getFleetStatusByVehicleType() {

        String jpql = "SELECT v.vehicleType, COUNT(v) FROM Vehicle v GROUP BY v.vehicleType ORDER BY CASE v.vehicleType WHEN 'SEDAN' THEN 1 WHEN 'MINIBUS' THEN 2 WHEN 'VAN' THEN 3 ELSE 4 END";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        return query.getResultList();
    }
}
