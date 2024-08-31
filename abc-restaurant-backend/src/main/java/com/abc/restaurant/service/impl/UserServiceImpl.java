package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.entity.Admin;
import com.abc.restaurant.entity.EmailPasswordResetOTP;
import com.abc.restaurant.entity.Staff;
import com.abc.restaurant.entity.User;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.exception.UserException;
import com.abc.restaurant.repository.AdminRepo;
import com.abc.restaurant.repository.EmailPasswordResetOTPRepo;
import com.abc.restaurant.repository.StaffRepo;
import com.abc.restaurant.repository.UserRepo;
import com.abc.restaurant.service.EmailService;
import com.abc.restaurant.service.UserService;
import com.abc.restaurant.util.OTPGenerator;
import com.abc.restaurant.util.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static com.abc.restaurant.constant.Constants.*;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final Validator validator;
    private final OTPGenerator otpGenerator;
//    private final EmailService emailService;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmailPasswordResetOTPRepo emailPasswordResetOTPRepo;

    @Autowired
    StaffRepo staffRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveUser(UserDTO userDTO) throws UserException {
        log.info("Save user function starts : {}", userDTO);
        try {
            if (userDTO.getUsername().isEmpty())
                throw new UserException(INVALID, false, "Username is required!");

            if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || !validator.isValidEmail(userDTO.getEmail()))
                throw new UserException(INVALID, false, "Please enter a valid email address.");

            if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty() || !validator.isValidPassword(userDTO.getPassword()))
                throw new UserException(INVALID, false, "Password must be at least 8 characters long and contain at least one number, one uppercase letter, one lowercase letter, and one special character (e.g., !@#$%^&*).");

//            validateUniqueEmail(userDTO.getEmail());

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

            User userEntity = modelMapper.map(userDTO, User.class);
            userEntity.setUserRole(UserRole.CUSTOMER);
            userEntity.setUserStatus(UserStatus.ACTIVE);
            userRepo.save(userEntity);

        } catch (Exception e) {
            log.info("User save process :{}", e.getMessage(), e);
            throw e;
        } catch (UserException e) {
            throw (e);
        }
    }

//    private void validateUniqueEmail(String email) {
//        if (adminRepo.findByEmail(email).isPresent() || staffRepo.findByEmail(email).isPresent() || userRepo.findByEmail(email).isPresent()) {
//            throw new ApplicationServiceException(200, false, "Email is already in use");
//        }
//    }

    @Override
    public List<UserDTO> getAllUsers() {
        return modelMapper.map(userRepo.findAll(), new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public UserDTO getUserByUsernameOrEmail(String username, String email) throws UserException {
        try {
            Optional<User> user = Optional.empty();

            // Determine which parameters are provided and query accordingly
            if (username != null && !username.isEmpty() && email != null && !email.isEmpty()) {
                // Both username and email are provided
                user = userRepo.findUserByUsernameAndEmail(username, email);
            } else if (username != null && !username.isEmpty()) {
                // Only username is provided
                user = userRepo.findUserByUsername(username);
            } else if (email != null && !email.isEmpty()) {
                // Only email is provided
                user = userRepo.findUserByEmail(email);
            }

            // Convert User entity to UserDTO if found
            if (user.isPresent())
                return modelMapper.map(user.get(), UserDTO.class);

            throw new UserException(NOT_FOUND, false, "Sorry, the user with the provided username or email was not found in our records. Please check the username or email and try again.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (UserException e) {
            throw e;
        }
    }

    @Override
    public UserDTO getUserById(Long id) throws UserException {
        try {
            Optional<User> byId = userRepo.findById(id);
            if (!byId.isPresent())
                return modelMapper.map(byId.get(), UserDTO.class);

            throw new UserException(NOT_FOUND, false, "Sorry, the user with the provided ID was not found in our records. Please check the ID and try again.");

        } catch (Exception e) {
            log.error("function getUserByUserId : {}", e.getMessage(), e);
            throw e;
        } catch (UserException e) {
            throw e;
        }
    }

    @Override
    public void updateUserStatus(Long id, UserStatus status) throws UserException {
        try {
            Optional<User> byId = userRepo.findById(id);
            if (!byId.isPresent())
                throw new UserException(NOT_FOUND, false ,"User not found!");

            userRepo.updateUserStatus(id, status.name());
        } catch (Exception | UserException e) {
            throw e;
        }
    }

    @Override
    public List<UserDTO> getAllUsersByStatus(UserStatus status) {
        return modelMapper.map(userRepo.findAllByUserStatus(status), new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public void resetUserPassword(String email, int otp, String password) throws UserException {
        try {
            Optional<EmailPasswordResetOTP> byEmailAndOtp = emailPasswordResetOTPRepo.findEmailPasswordResetOTPEntitiesByEmailAndOtp(email, String.valueOf(otp));

            if (!byEmailAndOtp.isPresent())
                throw new UserException(INVALID, false, "OTP verification failed.");

            Optional<Admin> admin = adminRepo.findByEmail(email);

            if (admin.isPresent() && admin.get().getStatus().equals(CommonStatus.ACTIVE)) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                admin.get().setPassword(bCryptPasswordEncoder.encode(password));
                adminRepo.save(admin.get());
                return;
            }

            Optional<User> customer = userRepo.findUserByEmail(email);

            if (customer.isPresent() && customer.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                customer.get().setPassword(bCryptPasswordEncoder.encode(password));
                userRepo.save(customer.get());
                return;
            }

            Optional<Staff> staff = staffRepo.findByEmail(email);

            if (staff.isPresent() && staff.get().getStatus().equals(CommonStatus.ACTIVE)) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                staff.get().setPassword(bCryptPasswordEncoder.encode(password));
                staffRepo.save(staff.get());
            }

        } catch (Exception | UserException e) {
            throw e;
        }
    }

//    @Override
//    public void sendUserOTP(String email) {
//        try {
//
//            int OTP = otpGenerator.generateOTP();
//            EmailPasswordResetOTP emailPasswordResetOTP = EmailPasswordResetOTP.builder().otp(String.valueOf(OTP)).email(email).build();
//            emailPasswordResetOTPRepo.deleteAll(emailPasswordResetOTPRepo.findEmailPasswordResetOTPEntitiesByEmail(email));
//            emailPasswordResetOTPRepo.save(emailPasswordResetOTP);
//
//            try {
//                emailService.sendUserOTPEmail(UserDTO.builder()
//                        .email(email)
//                        .build(), OTP);
//            } catch (Exception e) {
//                throw new ApplicationException(COMMON_ERROR_CODE, false, "Unable to send OTP");
//            }
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
}
