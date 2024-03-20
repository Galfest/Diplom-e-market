package ru.abramyants.emarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abramyants.emarket.api.CartDto;
import ru.abramyants.emarket.core.exceptions.ResourceNotFoundException;
import ru.abramyants.emarket.core.repositories.OrderRepository;
import ru.abramyants.emarket.core.entities.Order;
import ru.abramyants.emarket.core.entities.OrderItem;
import ru.abramyants.emarket.core.integrations.CartServiceIntegration;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public void createNewOrder(String username) {
        CartDto cart = cartServiceIntegration.getCurrentUserCart(username);
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Нельзя оформить заказ для пустой корзины");
        }
        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setUsername(username);
        order.setItems(new ArrayList<>());
        cart.getItems().forEach(ci -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            oi.setPricePerProduct(ci.getPricePerProduct());
            oi.setProduct(productService.findById(ci.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
            order.getItems().add(oi);
        });
        orderRepository.save(order);
        cartServiceIntegration.clearCart(username);
    }

    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
