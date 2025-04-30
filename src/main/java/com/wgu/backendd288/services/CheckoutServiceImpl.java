package com.wgu.backendd288.services;

import com.wgu.backendd288.dao.CartItemRepository;
import com.wgu.backendd288.dao.CartRepository;
import com.wgu.backendd288.dao.CustomerRepository;
import com.wgu.backendd288.entities.Cart;
import com.wgu.backendd288.entities.CartItem;
import com.wgu.backendd288.entities.Customer;
import com.wgu.backendd288.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository,
                               CartItemRepository cartItemRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));
        cart.setStatus(StatusType.ordered);

        cartRepository.save(cart);

        Customer customer = purchase.getCustomer();
        customer.add(cart);

        //customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
