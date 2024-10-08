package com.abc.restaurant.repository;

import com.abc.restaurant.entity.TableReservation;
import com.abc.restaurant.enums.TableReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableReservationRepo extends JpaRepository<TableReservation, Long> {
    List<TableReservation> findTableReservationByCustomerId(Long id);

    List<TableReservation> findTableReservationByTableReservationType(TableReservationType reservationType);

    @Query(value = "SELECT tables_reservation.* FROM tables_reservation WHERE tables_reservation.id=?1", nativeQuery = true)
    List<TableReservation> findByIdV2(Long id);
}
