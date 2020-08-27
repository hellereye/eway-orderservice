package com.mycloud.order.repository;

import com.mycloud.order.domain.SalesOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SalesOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
}
