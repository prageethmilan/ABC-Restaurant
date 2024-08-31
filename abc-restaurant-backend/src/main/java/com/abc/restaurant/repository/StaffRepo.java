package com.abc.restaurant.repository;

import com.abc.restaurant.entity.Staff;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.util.response.AdminStaffCommonResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmail(String email);

    @Query(value = "SELECT new com.abc.restaurant.util.response.AdminStaffCommonResponseDTO(se.id,se.name,se.employeeId,se.email,se.nic,se.phoneNumber,se.tempPassword,se.homeAddress,se.restaurant.id,se.status,se.userRole,se.createdDate,se.updatedDate) FROM Staff se")
    List<AdminStaffCommonResponseDTO> findAllAdminStaffCommonResDTO();

    Optional<Staff> findStaffEntityByIdAndStatus(Long id, CommonStatus status);
}
