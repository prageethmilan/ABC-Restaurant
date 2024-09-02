package com.abc.restaurant.repository;

import com.abc.restaurant.entity.Query;
import com.abc.restaurant.enums.QueryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepo extends JpaRepository<Query, Long> {
    List<Query> findAllByMenuItemsOrder_IdOrderByCreatedDate(Long id);

    List<Query> findAllByTableReservation_IdOrderByCreatedDate(Long id);

    List<Query> findQueryByQueryTypeAndUser_IdOrderByCreatedDate(QueryType type, Long id);
}
