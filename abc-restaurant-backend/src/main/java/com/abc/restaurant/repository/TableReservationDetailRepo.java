package com.abc.restaurant.repository;

import com.abc.restaurant.entity.TableReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableReservationDetailRepo extends JpaRepository<TableReservationDetail, Long> {
    List<TableReservationDetail> findByReservationId(Long id);
}
