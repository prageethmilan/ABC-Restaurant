package com.abc.restaurant.repository;

import com.abc.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
