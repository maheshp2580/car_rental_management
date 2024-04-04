package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.app.model.Payment;




@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long>{



}