package com.epam.lab.payments.mappers;


import com.epam.lab.payments.domain.OrderEntity;
import com.epam.lab.payments.dto.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDto(OrderEntity order);

    List<OrderDTO> ordersToOrdersDto(List<OrderEntity> orders);

    OrderEntity orderDtoToOrder(OrderDTO order);

    List<OrderEntity> ordersDtoToOrders(List<OrderDTO> orders);
}
