package com.abc.restaurant.repository;

import com.abc.restaurant.entity.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableReservationRepo extends JpaRepository<TableReservation, Long> {

}
