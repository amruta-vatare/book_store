package com.bridgelabz.bookstore.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.repository.order.model.OrderDetail;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long>{
    
}
