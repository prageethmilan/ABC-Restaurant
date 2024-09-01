package com.abc.restaurant.repository;

import com.abc.restaurant.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<MenuItem, Long> {
}
