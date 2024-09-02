package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.request.MenuItemOrderRequestDTO;
import com.abc.restaurant.dto.request.TableReservationRequestDTO;
import com.abc.restaurant.dto.response.TableReservationResponseDTO;
import com.abc.restaurant.entity.*;
import com.abc.restaurant.entity.MenuItem;
import com.abc.restaurant.enums.*;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.repository.*;
import com.abc.restaurant.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private TableReservationRepo tableReservationRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MenuItemOrderRepo menuItemOrderRepo;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private MenuItemOrderDetailRepo menuItemOrderDetailRepo;


    @Override
    public TableReservationResponseDTO saveTableReservation(TableReservationRequestDTO reqDTO) throws ApplicationException {
        try {
            String authentication = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> customer = userRepo.findByEmail(authentication);

            if (!customer.isPresent() || !customer.get().getUserStatus().equals(UserStatus.ACTIVE)){
                throw new ApplicationException(200, false, "Customer not found!");
            }

            Optional<Restaurant> restaurant = restaurantRepo.findById(reqDTO.getRestaurantId());

            if (!restaurant.isPresent() || !restaurant.get().getStatus().equals(CommonStatus.ACTIVE)){
                throw new ApplicationException(200, false, "Restaurant not found!");
            }

            TableReservation reservation = TableReservation.builder()
                    .reservationCode("Res/" + UUID.randomUUID())
                    .maxCount(reqDTO.getSeats())
                    .restaurant(restaurant.get())
                    .user(customer.get())
                    .reservedDate(reqDTO.getDate())
                    .customerNote(reqDTO.getNote())
                    .tableReservationType(reqDTO.getReservationType())
                    .status(CommonStatus.ACTIVE)
                    .operationalStatus(TableReservationOperationalStatus.NEW)
                    .build();

            TableReservation saved = tableReservationRepo.save(reservation);
            return modelMapper.map(saved, TableReservationResponseDTO.class);

        } catch (Exception e){
            throw e;
        } catch (ApplicationException e) {
            throw e;
        }
    }

    @Override
    public void saveMenuItemOrder(MenuItemOrderRequestDTO menuItemOrderRequestDTO) {
        try{
            String authentication = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> customer = userRepo.findByEmail(authentication);

            if (!customer.isPresent() || !customer.get().getUserStatus().equals(UserStatus.ACTIVE)){
                throw new ApplicationException(200, false, "Customer not found!");
            }

            Optional<Restaurant> restaurant = restaurantRepo.findById(menuItemOrderRequestDTO.getAddress().getRestaurantId());

            if (!restaurant.isPresent() || !restaurant.get().getStatus().equals(CommonStatus.ACTIVE)){
                throw new ApplicationException(200, false, "Restaurant not found!");
            }

            MenuItemsOrder itemsOrder = MenuItemsOrder.builder()
                    .orderId("OID-" + UUID.randomUUID())
                    .menuOrderType(MenuOrderType.ONLINE)
                    .operationalStatus(MenuOrderOperationalStatus.NEW)
                    .user(customer.get())
                    .restaurant(restaurant.get())
                    .status(CommonStatus.ACTIVE)
                    .deliveryAddress(menuItemOrderRequestDTO.getAddress().getAddress())
                    .fullName(menuItemOrderRequestDTO.getAddress().getFullName())
                    .mobileNumber(menuItemOrderRequestDTO.getAddress().getMobileNumber())
                    .paymentStatus(PaymentStatus.NOT_PAID)
                    .build();

            MenuItemsOrder saved = menuItemOrderRepo.save(itemsOrder);

            if (menuItemOrderRequestDTO.getItems() != null && !menuItemOrderRequestDTO.getItems().isEmpty()){
                List<MenuItemOrderDetail> orderDetails = menuItemOrderRequestDTO.getItems().stream().map(item -> {
                    Optional<MenuItem> menuItem = menuRepo.findById(item.getId());

                    if (!menuItem.isPresent() || !menuItem.get().getStatus().equals(CommonStatus.ACTIVE)) {
                        try {
                            throw new ApplicationException(200, false, "Meal not found!");
                        } catch (ApplicationException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    return MenuItemOrderDetail.builder()
                            .menuItem(menuItem.get())
                            .qty(item.getQty())
                            .price(menuItem.get().getDiscount() == null ? menuItem.get().getPrice() : menuItem.get().getPrice() - menuItem.get().getDiscount())
                            .discount(menuItem.get().getDiscount())
                            .menuItemsOrder(saved)
                            .build();
                }).collect(Collectors.toList());

                menuItemOrderDetailRepo.saveAll(orderDetails);
            } else {
                throw new ApplicationException(200, false, "No items found!");
            }

        } catch (Exception e) {
            System.out.println("Error while saving table reservation");
            throw e;
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getReservationsByType(QueryType type, Long id) {
//        try {
//            List<ReservationResDTO<?, ?>> reservationResDTOS = new ArrayList<>();
//
//            switch (type) {
//                case MEAL:
//                    reservationResDTOS = mealOrderRepo.findByUserEntityId(id).stream().map(mealOrderEntity -> {
//                        List<MealOrderDetail> byMealOrderId = mealOrderDetailRepo.findByMealOrderId(mealOrderEntity.getId());
//
//                        AtomicReference<Double> total = new AtomicReference<>((double) 0);
//
//                        List<MealResDTO> items = byMealOrderId.stream().map(mealOrderDetail -> {
//                            MealResDTO dto = modelMapper.map(mealOrderDetail.getMeal(), MealResDTO.class);
//                            dto.setQty(mealOrderDetail.getQty());
//                            total.updateAndGet(v -> v + mealOrderDetail.getPrice() * mealOrderDetail.getQty());
//                            return dto;
//                        }).collect(Collectors.toList());
//
//                        return ReservationResDTO.<MealReservationResDTO, MealResDTO>builder()
//                                .reservation(MealReservationResDTO.builder()
//                                        .id(mealOrderEntity.getId())
//                                        .total(total.get())
//                                        .orderId(mealOrderEntity.getOrderId())
//                                        .operationalStatus(mealOrderEntity.getOperationalStatus())
//                                        .status(mealOrderEntity.getStatus())
//                                        .mealOrderType(mealOrderEntity.getMealOrderType())
//                                        .userEntity(mealOrderEntity.getUserEntity().getId())
//                                        .restaurant(mealOrderEntity.getRestaurant().getId())
//                                        .createdDate(mealOrderEntity.getCreatedDate())
//                                        .updatedDate(mealOrderEntity.getUpdatedDate())
//                                        .build())
//                                .items(items)
//                                .queries(queryService.getQueries(QueryType.MEAL, mealOrderEntity.getId()))
//                                .build();
//                    }).collect(Collectors.toList());
//                    break;
//
//                case TABLE:
//                    reservationResDTOS = tableReservationRepo.findTableReservationEntityByCustomerId(id).stream().map(tableReservationEntity -> {
//                        List<TableReservationDetailEntity> byReservationId = tableReservationDetailRepo.findByReservationId(tableReservationEntity.getId());
//
//                        List<TableResDTO> items = byReservationId.stream().map(tableReservationDetailEntity -> {
//                            return modelMapper.map(tableReservationDetailEntity.getTable(), TableResDTO.class);
//                        }).collect(Collectors.toList());
//
//                        return ReservationResDTO.<TableReservationResDTO, TableResDTO>builder()
//                                .reservation(TableReservationResDTO.builder()
//                                        .id(tableReservationEntity.getId())
//                                        .reservationCode(tableReservationEntity.getReservationCode())
//                                        .max_count(tableReservationEntity.getMax_count())
//                                        .reservedDate(tableReservationEntity.getReservedDate())
//                                        .status(tableReservationEntity.getStatus())
//                                        .approvedBy(tableReservationEntity.getApprovedBy())
//                                        .approvedNote(tableReservationEntity.getApprovedNote())
//                                        .customerNote(tableReservationEntity.getCustomerNote())
//                                        .tableReservationType(tableReservationEntity.getTableReservationType())
//                                        .operationalStatus(tableReservationEntity.getOperationalStatus())
//                                        .createdDate(tableReservationEntity.getCreatedDate())
//                                        .updatedDate(tableReservationEntity.getUpdatedDate())
//                                        .build())
//                                .items(items)
//                                .queries(queryService.getQueries(QueryType.TABLE, tableReservationEntity.getId()))
//                                .build();
//                    }).collect(Collectors.toList());
//                    break;
//
//                case CUSTOM:
//                    reservationResDTOS.add(ReservationResDTO.builder()
//                            .queries(queryService.getQueries(QueryType.CUSTOM, id))
//                            .build());
//            }
//
//            return reservationResDTOS;
//
//        } catch (Exception e) {
//            log.error("Error while saving table reservation", e);
//            throw e;
//        }
    }
}
