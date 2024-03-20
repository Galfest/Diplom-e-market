package ru.abramyants.emarket.core.converters;

import org.springframework.stereotype.Component;
import ru.abramyants.emarket.api.OrderItemDto;
import ru.abramyants.emarket.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getProduct().getId(), o.getProduct().getTitle(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
