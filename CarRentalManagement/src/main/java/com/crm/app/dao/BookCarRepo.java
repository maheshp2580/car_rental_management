package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.crm.app.model.BookCar;





@Repository
public interface BookCarRepo extends JpaRepository<BookCar, Long>{



}