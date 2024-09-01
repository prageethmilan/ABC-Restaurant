package com.abc.restaurant.repository;

import com.abc.restaurant.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepo extends JpaRepository<Facility, Long> {
}
