package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crm.app.model.Driver;





@Repository
public interface DriverRepo extends JpaRepository<Driver, Long>{



}