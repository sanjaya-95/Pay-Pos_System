package com.example.paypos.repo;

import com.example.paypos.entity.Order;
import com.example.paypos.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailRepo extends JpaRepository<OrderDetails, Integer> {
}
