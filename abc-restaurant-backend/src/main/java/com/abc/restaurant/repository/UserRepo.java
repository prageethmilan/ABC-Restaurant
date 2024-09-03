package com.abc.restaurant.repository;

import com.abc.restaurant.entity.User;
import com.abc.restaurant.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByNameAndEmail(String username, String email);
    Optional<User> findUserByName(String username);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<User> findUserByIdAndUserStatus(Long id, UserStatus status);

    List<User> findAllByUserStatus(UserStatus userStatus);

    @Modifying
    @Query(value = "UPDATE user u SET u.user_status=?2 WHERE u.id=?1", nativeQuery = true)
    void updateUserStatus(Long id, String status);
}
