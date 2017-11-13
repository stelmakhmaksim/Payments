package com.epam.lab.payments.mappers;


import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.dto.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    List<OrderDTO> ordersToOrdersDto(List<OrderEntity> orders);

    OrderDTO orderToOrderDto(OrderEntity order);
}
