package com.food_order_api.User_Detail_API.Repository;

import com.food_order_api.User_Detail_API.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
