package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.dto.request.SaveQueryRequestDTO;
import com.abc.restaurant.dto.response.QueryResponseDTO;
import com.abc.restaurant.entity.*;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.repository.*;
import com.abc.restaurant.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {


    private final MenuItemOrderRepo menuItemOrderRepo;
    private final QueryRepo queryRepo;
    private final TableReservationRepo tableReservationRepo;
    private final AdminRepo adminRepo;
    private final StaffRepo staffRepo;
    private final UserRepo userRepo;

    @Override
    public List<QueryResponseDTO> getQueries(QueryType type, Long id) {
        try{
            List<Query> queryList = new ArrayList<>();

            switch (type){
                case MEAL:

                    Optional<MenuItemsOrder> menuOrder = menuItemOrderRepo.findById(id);

                    if (!menuOrder.isPresent()) {
                        throw new ApplicationException(200, false, "Meal order does not exist");
                    }

                    queryList = queryRepo.findAllByMenuItemsOrder_IdOrderByCreatedDate(id);
                    break;

                case TABLE:
                    Optional<TableReservation> tableReservation = tableReservationRepo.findById(id);

                    if (!tableReservation.isPresent()) {
                        throw new ApplicationException(200, false, "Meal order does not exist");
                    }

                    queryList = queryRepo.findAllByTableReservation_IdOrderByCreatedDate(id);
                    break;

                case CUSTOM:
                    queryList = queryRepo.findQueryByQueryTypeAndUser_IdOrderByCreatedDate(QueryType.CUSTOM, id);
                    break;
            }

            return queryList.stream().map(query -> QueryResponseDTO.builder()
                    .id(query.getId())
                    .mealOrder(query.getMenuItemsOrder() == null ? null : query.getMenuItemsOrder().getId())
                    .tableReservation(query.getTableReservation() == null ? null : query.getTableReservation().getId())
                    .queryType(query.getQueryType())
                    .message(query.getMessage())
                    .user(query.getUser() == null ? null : UserDTO.builder()
                            .id(query.getUser().getId())
                            .name(query.getUser().getName())
                            .build())
                    .admin(query.getAdmin() == null ? null : UserDTO.builder()
                            .id(query.getAdmin().getId())
                            .name(query.getAdmin().getName())
                            .build())
                    .staff(query.getStaff() == null ? null : UserDTO.builder()
                            .id(query.getStaff().getId())
                            .name(query.getStaff().getName())
                            .build())
                    .userRole(query.getUserRole())
                    .createdDate(query.getCreatedDate())
                    .updatedDate(query.getUpdatedDate())
                    .status(query.getStatus())
                    .build()).collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(SaveQueryRequestDTO saveQueryRequestDTO) {
        try {
            Query query = new Query();

            // set query user
            switch (saveQueryRequestDTO.getUserRole()) {
                case ADMIN:
                    Optional<Admin> admin = adminRepo.findAdminEntityByIdAndStatus(saveQueryRequestDTO.getUserId(), CommonStatus.ACTIVE);
                    if (!admin.isPresent()) {
                        throw new ApplicationException(200, false, "Admin does not exist");
                    }
                    query.setAdmin(admin.get());
                    break;

                case STAFF:
                    Optional<Staff> staffEntity = staffRepo.findStaffEntityByIdAndStatus(saveQueryRequestDTO.getUserId(), CommonStatus.ACTIVE);
                    if (!staffEntity.isPresent()) {
                        throw new ApplicationException(200, false, "Staff does not exist");
                    }
                    query.setStaff(staffEntity.get());
                    break;

                case CUSTOMER:
                    Optional<User> user = userRepo.findUserByIdAndUserStatus(saveQueryRequestDTO.getUserId(), UserStatus.ACTIVE);
                    if (!user.isPresent()) {
                        throw new ApplicationException(200, false, "Customer does not exist");
                    }
                    query.setUser(user.get());
                    break;

                default:
                    throw new ApplicationException(200, false, "Invalid user role!");
            }

            // set query related order
            switch (saveQueryRequestDTO.getQueryType()) {
                case MEAL:
                    Optional<MenuItemsOrder> mealOrder = menuItemOrderRepo.findById(saveQueryRequestDTO.getMealOrderId());

                    if (!mealOrder.isPresent()) {
                        throw new ApplicationException(200, false, "Meal order does not exist");
                    }

                    query.setMenuItemsOrder(mealOrder.get());
                    break;

                case TABLE:
                    Optional<TableReservation> tableReservation = tableReservationRepo.findById(saveQueryRequestDTO.getTableReservationId());

                    if (!tableReservation.isPresent()) {
                        throw new ApplicationException(200, false, "Meal order does not exist");
                    }

                    query.setTableReservation(tableReservation.get());
                    break;

                case CUSTOM:

                    if (!saveQueryRequestDTO.getUserRole().equals(UserRole.CUSTOMER)) {
                        Optional<User> userEntity = userRepo.findById(saveQueryRequestDTO.getRepliedTo());

                        if (!userEntity.isPresent()) {
                            throw new ApplicationException(200, false, "Customer does not exist");
                        }

                        query.setUser(userEntity.get());
                    }
                    break;

                default:
                    throw new ApplicationException(200, false, "Invalid query type!");
            }

            query.setMessage(saveQueryRequestDTO.getMessage());
            query.setQueryType(saveQueryRequestDTO.getQueryType());
            query.setUserRole(saveQueryRequestDTO.getUserRole());
            query.setStatus(CommonStatus.ACTIVE);
            queryRepo.save(query);

        } catch (Exception e) {
            throw e;
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }
}
