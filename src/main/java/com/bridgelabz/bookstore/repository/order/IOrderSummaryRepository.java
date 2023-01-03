package com.bridgelabz.bookstore.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.bookstore.repository.order.model.OrderSummary;

@Repository
public interface IOrderSummaryRepository extends JpaRepository<OrderSummary,Long> {
    @Query(value = "select * from order_summary where user_id = :user_id",nativeQuery = true)
    List<OrderSummary> findOrdersByUserId(@Param("user_id") Long user_id);

    @Modifying
    @Transactional
    @Query(value = "update order_summary set cancelled = 1  where order_id = :order_id",nativeQuery = true)
    void updateStatusByOrderId(@Param("order_id") Long orderId);

}
