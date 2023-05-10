package com.psd.eventplanner.repository;

import com.psd.eventplanner.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public abstract Customer findCustomerById(Long id);
}
