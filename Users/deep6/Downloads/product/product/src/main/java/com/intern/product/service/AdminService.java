package com.intern.product.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

    Map<String, Object> getDashboardStats();

    List<Object[]> getMonthlySales();

    List<Object[]> getTopProducts();
}