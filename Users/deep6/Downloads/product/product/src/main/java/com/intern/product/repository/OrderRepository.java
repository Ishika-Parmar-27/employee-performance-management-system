package com.intern.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intern.product.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    Double getTotalRevenue();

    @Query("""
           SELECT MONTH(o.orderDate), SUM(o.totalAmount)
           FROM Order o
           GROUP BY MONTH(o.orderDate)
           """)
    List<Object[]> getMonthlySales();
}