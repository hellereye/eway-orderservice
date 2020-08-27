package com.mycloud.order.repository;

import com.mycloud.order.domain.SalesOrderItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SalesOrderItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, Long> {
}
