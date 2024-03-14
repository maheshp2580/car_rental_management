package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.app.model.Car;
import com.crm.app.model.Feedback;
import com.crm.app.model.User;




@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long>{



}