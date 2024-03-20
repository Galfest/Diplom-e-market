package ru.abramyants.emarket.cart.converters;

import org.springframework.stereotype.Component;
import ru.abramyants.emarket.api.CartItemDto;
import ru.abramyants.emarket.cart.utils.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem c) {
        return new CartItemDto(c.getProductId(), c.getProductTitle(), c.getQuantity(), c.getPricePerProduct(), c.getPrice());
    }
}
