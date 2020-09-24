package com.mycloud.order.repository;

import com.mycloud.order.domain.SalesOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the SalesOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    /**
     * 当 one2many时并fetch=FetchType.EAGER 会出现N+1问题
     * 使用EntityGraph(value = "order.orderItems", type = EntityGraph.EntityGraphType.FETCH)
     * 使N+1，转化为left outer join
     * Hibernate:
     *     select
     *         salesorder0_.id as id1_2_0_,
     *         orderitems1_.id as id1_3_1_,
     *         salesorder0_.amount as amount2_2_0_,
     *         salesorder0_.customer_no as customer3_2_0_,
     *         orderitems1_.sales_order_id as sales_o15_3_0__,
     *         orderitems1_.id as id1_3_0__
     *     from
     *         sales_order salesorder0_
     *     left outer join
     *         sales_order_item orderitems1_
     *             on salesorder0_.id=orderitems1_.sales_order_id
     * @return
     */
    @Override
    @EntityGraph(value = "order.orderItems", type = EntityGraph.EntityGraphType.FETCH)//
    List<SalesOrder> findAll();//查询所以记录不能急迫加载，原因在于对性能的影响



//    @Override
//    @EntityGraph(value = "order.orderItems" )
//    @Query(value = "SELECT e FROM SalesOrder e left join fetch e.orderItems",
//        countQuery = "select count(e) from SalesOrder e")
//    Page<SalesOrder> findAll(Pageable pageable);
}
