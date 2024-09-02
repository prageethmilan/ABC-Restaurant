package com.abc.restaurant.repository;

import com.abc.restaurant.entity.MenuItemOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemOrderDetailRepo extends JpaRepository<MenuItemOrderDetail, Long> {

    List<MenuItemOrderDetail> findByMenuItemsOrderId(Long id);
}
