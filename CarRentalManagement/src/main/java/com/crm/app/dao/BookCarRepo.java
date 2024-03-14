package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.app.model.BookCar;
import com.crm.app.model.Car;
import com.crm.app.model.User;




@Repository
public interface BookCarRepo extends JpaRepository<BookCar, Long>{

	@Query( value = "select * from car_bookings where id = :id", nativeQuery = true)
	BookCar findBookCarById(@Param("id") Long id);



}