package com.bridgelabz.bookstore.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.repository.user.model.UserData;

@Repository
public interface IUserRepository extends JpaRepository<UserData,Long>{
    @Query(value = "select * from user_data user where user.email=:email_id",nativeQuery = true)
    UserData findUserByEmail(@Param("email_id") String email_id);

    
}
