package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.request.SaveAdminRequestDTO;
import com.abc.restaurant.dto.response.AdminStaffCommonResponseDTO;
import com.abc.restaurant.entity.Admin;
import com.abc.restaurant.entity.Restaurant;
import com.abc.restaurant.entity.Staff;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.exception.UserException;
import com.abc.restaurant.repository.AdminRepo;
import com.abc.restaurant.repository.RestaurantRepo;
import com.abc.restaurant.repository.StaffRepo;
import com.abc.restaurant.repository.UserRepo;
import com.abc.restaurant.service.AdminService;
import com.abc.restaurant.util.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.abc.restaurant.constant.Constants.INVALID;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Validator validator;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public void saveAdmin(SaveAdminRequestDTO saveAdminRequestDTO) throws ApplicationException, UserException {
        try {
            if (saveAdminRequestDTO.getId() == 0) {
                System.out.println(saveAdminRequestDTO.toString());
                if (saveAdminRequestDTO.getEmail() == null || saveAdminRequestDTO.getEmail().isEmpty() || !validator.isValidEmail(saveAdminRequestDTO.getEmail()))
                    throw new UserException(INVALID, false, "Please enter a valid email address.");

                if (saveAdminRequestDTO.getPassword() == null || saveAdminRequestDTO.getPassword().isEmpty() || !validator.isValidPassword(saveAdminRequestDTO.getPassword()))
                    throw new UserException(INVALID, false, "Password must be at least 8 characters long and contain at least one number, one uppercase letter, one lowercase letter, and one special character (e.g., !@#$%^&*).");
                validateUniqueEmail(saveAdminRequestDTO.getEmail());

                switch (saveAdminRequestDTO.getRole()) {
                    case ADMIN:
                        System.out.println(saveAdminRequestDTO.getRole());
                        Admin newAdmin = Admin.builder()
                                .name(saveAdminRequestDTO.getName())
                                .email(saveAdminRequestDTO.getEmail())
                                .homeAddress(saveAdminRequestDTO.getHomeAddress())
                                .nic(saveAdminRequestDTO.getNic())
                                .phoneNumber(saveAdminRequestDTO.getPhoneNumber())
                                .password(bCryptPasswordEncoder.encode(saveAdminRequestDTO.getPassword()))
                                .tempPassword(saveAdminRequestDTO.getPassword())
                                .userRole(UserRole.ADMIN)
                                .status(saveAdminRequestDTO.getStatus())
                                .build();

                        adminRepo.save(newAdmin);
                        break;

                    case STAFF:
                        Optional<Restaurant> restaurant = restaurantRepo.findById(saveAdminRequestDTO.getRestaurantId());

                        if (!restaurant.isPresent() || restaurant.get().getStatus().equals(CommonStatus.DELETED)) {
                            throw new ApplicationException(200, false, "Restaurant not found");
                        }

                        Staff newStaff = Staff.builder()
                                .name(saveAdminRequestDTO.getName())
                                .employeeId("STAFF-" + UUID.randomUUID() + "-" + new Date())
                                .restaurant(restaurant.get())
                                .email(saveAdminRequestDTO.getEmail())
                                .homeAddress(saveAdminRequestDTO.getHomeAddress())
                                .nic(saveAdminRequestDTO.getNic())
                                .phoneNumber(saveAdminRequestDTO.getPhoneNumber())
                                .password(bCryptPasswordEncoder.encode(saveAdminRequestDTO.getPassword()))
                                .tempPassword(saveAdminRequestDTO.getPassword())
                                .userRole(UserRole.STAFF)
                                .status(saveAdminRequestDTO.getStatus())
                                .build();

                        staffRepo.save(newStaff);
                        break;

                    default:
                        throw new ApplicationException(200, false, "Invalid Role");
                }
            } else {
                switch (saveAdminRequestDTO.getRole()){
                    case ADMIN:
                        Admin existingAdmin = adminRepo.findById(saveAdminRequestDTO.getId()).orElseThrow(() -> new ApplicationException(200, false, "Can not find user account"));

                        existingAdmin.setName(saveAdminRequestDTO.getName());
                        existingAdmin.setHomeAddress(saveAdminRequestDTO.getHomeAddress());
                        existingAdmin.setNic(saveAdminRequestDTO.getNic());
                        existingAdmin.setPhoneNumber(saveAdminRequestDTO.getPhoneNumber());
                        existingAdmin.setPassword(bCryptPasswordEncoder.encode(saveAdminRequestDTO.getPassword()));
                        existingAdmin.setTempPassword(saveAdminRequestDTO.getPassword());
                        existingAdmin.setStatus(saveAdminRequestDTO.getStatus());
                        existingAdmin.setUpdatedDate(new Date());

                        adminRepo.save(existingAdmin);
                        break;

                    case STAFF:
                        Staff existingStaff = staffRepo.findById(saveAdminRequestDTO.getId()).orElseThrow(() -> new ApplicationException(200, false, "Can not find user account"));

                        Optional<Restaurant> restaurant = restaurantRepo.findById(saveAdminRequestDTO.getRestaurantId());

                        if (!restaurant.isPresent() || restaurant.get().getStatus().equals(CommonStatus.DELETED)){
                            throw new ApplicationException(200, false, "Restaurant not found!");
                        }

                        existingStaff.setName(saveAdminRequestDTO.getName());
                        existingStaff.setHomeAddress(saveAdminRequestDTO.getHomeAddress());
                        existingStaff.setNic(saveAdminRequestDTO.getNic());
                        existingStaff.setPhoneNumber(saveAdminRequestDTO.getPhoneNumber());
                        existingStaff.setPassword(bCryptPasswordEncoder.encode(saveAdminRequestDTO.getPassword()));
                        existingStaff.setTempPassword(saveAdminRequestDTO.getPassword());
                        existingStaff.setRestaurant(restaurant.get());
                        existingStaff.setStatus(saveAdminRequestDTO.getStatus());
                        existingStaff.setUpdatedDate(new Date());

                        staffRepo.save(existingStaff);
                        break;

                    default:
                        throw new ApplicationException(200, false, "Invalid role");
                }
            }
        } catch (Exception e) {
            throw e;
        } catch (ApplicationException e) {
            throw e;
        } catch (UserException e) {
            throw e;
        }
    }

    private void validateUniqueEmail(String email) throws ApplicationException {
        if (adminRepo.findByEmail(email).isPresent() || staffRepo.findByEmail(email).isPresent() || userRepo.findUserByEmail(email).isPresent()){
            throw new ApplicationException(200, false, "Email is already in use.");
        }
    }

    @Override
    public List<Object> getAllAdminPortalUsers() {
        try {
            List<AdminStaffCommonResponseDTO> staffCommonResDTO = staffRepo.findAllAdminStaffCommonResDTO();
            List<AdminStaffCommonResponseDTO> adminCommonResDTO = adminRepo.findAllAdminStaffCommonResDTO();

            List<Object> allPortalUsers = new ArrayList<>();
            allPortalUsers.addAll(staffCommonResDTO);
            allPortalUsers.addAll(adminCommonResDTO);

            return allPortalUsers;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Object findAdminPortalUserByEmail(String email) throws ApplicationException {
        try {
            Optional<Admin> admin = adminRepo.findByEmail(email);
            if (admin.isPresent()) {
                return mapToAdminStaffCommonResponseDTO(admin.get());
            }

            Optional<Staff> staff = staffRepo.findByEmail(email);
            if (staff.isPresent()){
                return mapToAdminStaffCommonResponseDTO(staff.get());
            }

            throw new ApplicationException(404, false, "User not found!");
        } catch (Exception e) {
            throw e;
        } catch (ApplicationException e) {
            throw e;
        }
    }

    private AdminStaffCommonResponseDTO mapToAdminStaffCommonResponseDTO(Admin admin) {
        return AdminStaffCommonResponseDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .nic(admin.getNic())
                .phoneNumber(admin.getPhoneNumber())
                .tempPassword(admin.getTempPassword())
                .homeAddress(admin.getHomeAddress())
                .status(admin.getStatus())
                .userRole(admin.getUserRole())
                .createdDate(admin.getCreatedDate())
                .updatedDate(admin.getUpdatedDate())
                .build();
    }

    private AdminStaffCommonResponseDTO mapToAdminStaffCommonResponseDTO(Staff staff) {
        return AdminStaffCommonResponseDTO.builder()
                .id(staff.getId())
                .employeeId(staff.getEmployeeId())
                .name(staff.getName())
                .email(staff.getEmail())
                .nic(staff.getNic())
                .phoneNumber(staff.getPhoneNumber())
                .restaurantId(staff.getRestaurant().getId())
                .tempPassword(staff.getTempPassword())
                .homeAddress(staff.getHomeAddress())
                .status(staff.getStatus())
                .userRole(staff.getUserRole())
                .createdDate(staff.getCreatedDate())
                .updatedDate(staff.getUpdatedDate())
                .build();
    }
}
