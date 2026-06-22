package com.intern.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.product.repository.OrderRepository;
import com.intern.product.repository.ProductRepository;
import com.intern.product.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map<String, Object> getDashboardStats() {

        Map<String, Object> map = new HashMap<>();

        map.put("totalUsers", userRepository.count());
        map.put("totalProducts", productRepository.count());
        map.put("totalOrders", orderRepository.count());
        map.put("totalRevenue", orderRepository.getTotalRevenue());

        return map;
    }

    @Override
    public List<Object[]> getMonthlySales() {
        return orderRepository.getMonthlySales();
    }

    @Override
    public List<Object[]> getTopProducts() {
        return productRepository.getTopProducts();
    }
}