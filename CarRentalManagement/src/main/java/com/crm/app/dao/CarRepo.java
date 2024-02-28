package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crm.app.model.Car;





@Repository
public interface CarRepo extends JpaRepository<Car, Long>{



}