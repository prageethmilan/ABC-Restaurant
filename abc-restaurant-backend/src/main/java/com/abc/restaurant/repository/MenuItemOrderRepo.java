package com.abc.restaurant.repository;

import com.abc.restaurant.entity.MenuItemsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemOrderRepo extends JpaRepository<MenuItemsOrder, Long> {

    List<MenuItemsOrder> findByUserId(Long id);

    @Query(value = "SELECT meal_order.* FROM meal_order WHERE meal_order.id=?1", nativeQuery = true)
    List<MenuItemsOrder> findByIdV2(Long id);
}
