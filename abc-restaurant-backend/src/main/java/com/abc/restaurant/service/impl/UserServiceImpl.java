package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.entity.User;
import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.UserException;
import com.abc.restaurant.repository.UserRepo;
import com.abc.restaurant.service.UserService;
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

import static com.abc.restaurant.constant.Constants.INVALID;
import static com.abc.restaurant.constant.Constants.NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final Validator validator;

    @Autowired
    UserRepo userRepo;

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

            throw new UserException(NOT_FOUND, false, "Sorry, the user with the provided ID was not found in our records. Please check the ID and try again.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (UserException e) {
            throw e;
        }
    }
}
