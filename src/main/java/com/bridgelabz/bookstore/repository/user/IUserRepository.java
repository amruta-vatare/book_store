package com.bridgelabz.bookstore.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.repository.user.model.UserData;

@Repository
public interface IUserRepository extends JpaRepository<UserData,Long>{
    
}
