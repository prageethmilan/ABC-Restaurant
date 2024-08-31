package com.abc.restaurant.repository;

import com.abc.restaurant.entity.Admin;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.util.response.AdminStaffCommonResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);

    @Query(value = "SELECT new com.abc.restaurant.util.response.AdminStaffCommonResponseDTO(ae.id,ae.name,ae.email,ae.nic,ae.phoneNumber,ae.tempPassword,ae.homeAddress,ae.status,ae.userRole,ae.createdDate,ae.updatedDate) FROM Admin ae")
    List<AdminStaffCommonResponseDTO> findAllAdminStaffCommonResDTO();

    Optional<Admin> findAdminEntityByIdAndStatus(Long id, CommonStatus status);
}