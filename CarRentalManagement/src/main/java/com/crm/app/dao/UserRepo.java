package com.crm.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.app.model.User;




@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	@Query( value = "select * from users where email = :email", nativeQuery = true)
	User findbyEmail(@Param("email") String email);

	@Query( value = "select * from users where id = :id", nativeQuery = true)
	User findUserById(@Param("id") Long id);


}